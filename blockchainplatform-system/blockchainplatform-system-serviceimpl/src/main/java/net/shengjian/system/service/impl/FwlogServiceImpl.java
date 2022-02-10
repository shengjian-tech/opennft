package net.shengjian.system.service.impl;

import net.shengjian.frame.entity.IBaseEntity;
import net.shengjian.system.entity.Fwlog;
import net.shengjian.system.service.IFwlogService;
import org.springframework.stereotype.Service;

/**
 * TODO 在此加入类描述
 *
 * @author jiagou.com<Auto generate>
 * @version 2013-07-29 11:36:44
 */
@Service("fwlogService")
public class FwlogServiceImpl extends BaseSpringrainServiceImpl implements IFwlogService {

    @Override
    public String save(IBaseEntity entity) throws Exception {
        Fwlog fwlog = (Fwlog) entity;
        return (String) super.save(fwlog);
    }


    @Override
    public Integer update(IBaseEntity entity) throws Exception {
        Fwlog fwlog = (Fwlog) entity;
        return super.update(fwlog);
    }

    @Override
    public Fwlog findFwlogById(Object id) throws Exception {

        if (id == null) {
            return null;
        }
        Fwlog fwLog = new Fwlog();
        fwLog.setId(id.toString());
        /*
         * //使用finder 构建查询语句 Finder finder=new Finder("SELECT * FROM ");
         * //确定年度分表,实际可以根据ID的前四位确定年份,例如,我的Id前四位是2013 就是2013年的数据
         * finder.append("t_fwlog").append(fwLog.getExt()); //添加where 条件
         * finder.append(" WHERE id=:id"); //设置参数值 finder.setParam("id", id.toString());
         * return super.queryForObject(finder, Fwlog.class);
         */

        return super.queryForObject(fwLog);

    }


}
