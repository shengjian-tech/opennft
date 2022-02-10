package net.shengjian.system.service.impl;

import net.shengjian.frame.entity.AuditLog;
import net.shengjian.system.service.IAuditlogService;
import org.springframework.stereotype.Service;

/**
 * TODO 在此加入类描述
 *
 * @author springrain<Auto generate>
 * @version 2013-04-02 10:17:31
 * @copyright {@link jiagou.com}
 * @see AuditlogServiceImpl
 */
@Service("auditlogService")
public class AuditlogServiceImpl extends BaseSpringrainServiceImpl implements IAuditlogService {

    @Override
    public String saveAuditlog(AuditLog entity) throws Exception {
        return (String) super.save(entity);
    }


    @Override
    public Integer updateAuditlog(AuditLog entity) throws Exception {
        return super.update(entity);
    }

    @Override
    public AuditLog findAuditlogById(Object id) throws Exception {

        if (id == null) {
            return null;
        }

        AuditLog autidLog = new AuditLog();
        autidLog.setId(id.toString());

        /*
         * //使用finder 构建查询语句 Finder finder=new Finder("SELECT * FROM ");
         * //确定年度分表,实际可以根据ID的前四位确定年份,例如,我的Id前四位是2013 就是2013年的数据
         * finder.append("t_auditlog").append(autidLog.getExt()); //添加where 条件
         * finder.append(" WHERE id=:id"); //设置参数值 finder.setParam("id", id.toString());
         * return super.queryForObject(finder, AuditLog.class);
         */
        return super.queryForObject(autidLog);

    }
}
