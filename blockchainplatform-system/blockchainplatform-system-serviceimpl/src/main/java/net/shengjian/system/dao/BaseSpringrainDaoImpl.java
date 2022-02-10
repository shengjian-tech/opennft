package net.shengjian.system.dao;

import net.shengjian.frame.dao.BaseJdbcDaoImpl;
import net.shengjian.frame.dao.IBaseJdbcDao;
import net.shengjian.frame.dao.dialect.IDialect;
import net.shengjian.frame.entity.AuditLog;
import net.shengjian.frame.util.SpringUtils;
import net.shengjian.rpc.sessionuser.SessionUser;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * springrain项目的基础Dao,代理demo数据库
 *
 * @author springrain<Auto generate>
 * @version 2013-03-19 11:08:15
 * @see BaseSpringrainDaoImpl
 */
@Repository("baseSpringrainDao")
public class BaseSpringrainDaoImpl extends BaseJdbcDaoImpl implements IBaseJdbcDao {

    /**
     * demo 数据库的jdbcCall,对应 spring配置的 jdbcCall bean
     *
     * @Resource 这样注入 jdbcCall 还是单例,原因待查......
     * 使用LookUp
     */
    //@Resource
    public SimpleJdbcCall jdbcCall;
    /**
     * mysqlDialect 是mysql的方言,springBean的name,可以参考 IDialect的实现.通过dbDialectBeanName 获取实际的bean
     */
    //@Resource
    //public IDialect dbDialect;


    /**
     * 数据库方言,和IDialect接口的实现bean名称保持一致
     */
    @Value("${springrain.dbDialectBeanName:mysqlDialect}")
    private String dbDialectBeanName;


    /**
     * demo 数据库的jdbc,对应 spring配置的 jdbc bean
     */
    @Resource
    NamedParameterJdbcTemplate jdbc;

    public BaseSpringrainDaoImpl() {
    }

    @Override
    public IDialect getDialect() {

        IDialect dbDialect = (IDialect) SpringUtils.getBean(dbDialectBeanName);

        return dbDialect;
    }

    /**
     * 实现父类方法,springrain数据库的jdbcCall,对应 spring配置的 jdbcCall bean
     */
    @Override
    @Lookup //使用 @Lookup注解,实现获取是新的bean对象.
    public SimpleJdbcCall getJdbcCall() {
        //直接@Resource注入还是一个单例,手动获取新的bean对象
        //SimpleJdbcCall jdbcCall=(SimpleJdbcCall)SpringUtils.getBean("jdbcCall");
        return this.jdbcCall;
    }

    /**
     * 实现父类方法,springrain数据库的jdbcCall,对应 spring配置的 jdbc bean
     */
    @Override
    public NamedParameterJdbcTemplate getJdbc() {
        return jdbc;
    }

    /*
     * 读写分离时,处理同步延迟问题 ++
     *
     * @Override public NamedParameterJdbcTemplate getJdbc() { try{
     * TransactionInterceptor.currentTransactionStatus();
     * }catch(NoTransactionException e){ return this.jdbc; } return getWriteJdbc();
     * }
     */

    /**
     * 实现父类方法,返回记录日志的Entity接口实现,reutrn null 则代表不记录日志
     */
    @Override
    public AuditLog getAuditLog() {
        return null;
    }

    /**
     * 是否打印sql语句,使用GlobalStatic.showsql
     *
     * @return
     */
    //@Override
    //public boolean showsql() {
    //	return true;
    //}
    @Override
    public String getUserName() {
        return SessionUser.getUserName();
    }


    public String getDbDialectBeanName() {
        return dbDialectBeanName;
    }

    public void setDbDialectBeanName(String dbDialectBeanName) {
        this.dbDialectBeanName = dbDialectBeanName;
    }


}
