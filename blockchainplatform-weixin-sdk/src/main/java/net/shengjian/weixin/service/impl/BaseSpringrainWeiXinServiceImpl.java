package net.shengjian.weixin.service.impl;

import net.shengjian.frame.dao.IBaseJdbcDao;
import net.shengjian.frame.service.BaseServiceImpl;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Resource;

public class BaseSpringrainWeiXinServiceImpl extends BaseServiceImpl {

    @Resource
    @Lazy
    IBaseJdbcDao baseSpringrainDao;

    public BaseSpringrainWeiXinServiceImpl() {
    }

    @Override
    public IBaseJdbcDao getBaseDao() {
        return baseSpringrainDao;
    }
}
