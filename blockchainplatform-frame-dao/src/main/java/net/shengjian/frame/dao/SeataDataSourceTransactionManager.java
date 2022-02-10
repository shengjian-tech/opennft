package net.shengjian.frame.dao;

import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransaction;
import io.seata.tm.api.GlobalTransactionContext;
import net.shengjian.frame.util.GlobalStatic;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.lang.Nullable;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;

/**
 * 分布式事务,一定要避免A服务update表t,RPC调用B服务,B服务也update表t.这样A等待B结果,B等待A释放锁,造成死锁.
 * <p>
 * seata和spring事务混合使用,spring事务开启-->seata事务开启-->spring事务提交-->seata事务提交.
 * 虽然存在提交或者回滚时状态不一致的风险,但是无注解,可以动态开启seata事务.敏感操作建议使用@GlobalTransactional注解
 * <p>
 * 清铭大佬:这样写的风险:如果本地事务提交成功,分布式事务未提交成功-->无风险,分布式事务数据已经在一阶段落地.
 * 本地事务提交失败,分布式事务未回滚成功-->有风险,导致分布式事务未回滚成功原因：外部修改数据,回滚时数据校验不多,回滚失败不重试.
 * 框架网络或其他问题,一直重试但存在重试不成功风险,比如客户端宕机了,这样数据不一致的,但对于本地事务即使客户端宕机了也可以基于连接的回滚.
 *
 * @author caomei
 */

public class SeataDataSourceTransactionManager extends DataSourceTransactionManager {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(SeataDataSourceTransactionManager.class);

    @Override
    protected void doCommit(DefaultTransactionStatus status) {

        // 先提交spring事务.
        super.doCommit(status);

        // 当前线程是否是seata的分支事务,和spring事务存在同步风险,需要记录好日志.敏感操作建议使用@GlobalTransactional注解
        Boolean branch = GlobalStatic.seataBranchTransaction.get();
        if (branch == null) {
            branch = false;
        }
        if (GlobalStatic.seataEnable && !branch && RootContext.inGlobalTransaction()) {
            try {
                // 分支事务执行时把事务角色修改成了GlobalTransactionRole.Participant,reload重新设置成GlobalTransactionRole.Launcher
                //GlobalTransaction tx = GlobalTransactionContext.reload(RootContext.getXID());
                GlobalTransaction tx = GlobalTransactionContext.getCurrent();
                tx.commit();

            } catch (TransactionException txe) {
                logger.error(txe.getMessage(), txe);
            }

        }


    }

    @Override
    protected void doRollback(DefaultTransactionStatus status) {
        // 先回滚spring事务
        super.doRollback(status);

        // 回滚seata事务.
        if (GlobalStatic.seataEnable && RootContext.inGlobalTransaction()) {
            try {
                // 分支事务执行把,把事务角色修改成了GlobalTransactionRole.Participant,reload重新设置成GlobalTransactionRole.Launcher
                Boolean branch = GlobalStatic.seataBranchTransaction.get();
                GlobalTransaction tx = null;
                if (branch == null) {
                    branch = false;
                }
                if (branch) {
                    tx = GlobalTransactionContext.reload(RootContext.getXID());
                } else {
                    tx = GlobalTransactionContext.getCurrent();
                }

                tx.rollback();
            } catch (TransactionException txe) {
                logger.error(txe.getMessage(), txe);
            }

        }

    }

    @Override
    protected void doCleanupAfterCompletion(Object transaction) {
        super.doCleanupAfterCompletion(transaction);
        GlobalStatic.seataBranchTransaction.remove();
    }

    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {
        // 如果禁用了分布式事务或者已经在分布事务中
        if ((!GlobalStatic.seataEnable) || RootContext.inGlobalTransaction()) {
            //开启spring事务
            super.doBegin(transaction, definition);
            return;
        }
        //新建分布式事务
        GlobalTransaction tx = GlobalTransactionContext.createNew();
        try {
            //开启分布式事务
            tx.begin();
            super.doBegin(transaction, definition);
        } catch (TransactionException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    protected Object doSuspend(Object transaction) {
        return super.doSuspend(transaction);
    }

    @Override
    protected void doResume(@Nullable Object transaction, Object suspendedResources) {
        super.doResume(transaction, suspendedResources);
    }


    @Override
    protected void doSetRollbackOnly(DefaultTransactionStatus status) {
        super.doSetRollbackOnly(status);
    }


    private void unbindtx() {

        if (!GlobalStatic.seataEnable) {
            return;
        }

        // 获取全局的xid
        String txGroupId = RootContext.getXID();
        if (RootContext.inGlobalTransaction() == false || StringUtils.isBlank(txGroupId)) {
            return;
        }
        String unbindXid = RootContext.unbind();
        if (!txGroupId.equalsIgnoreCase(unbindXid)) {
            if (unbindXid != null) {
                RootContext.bind(unbindXid);
            }
        }

    }

}
