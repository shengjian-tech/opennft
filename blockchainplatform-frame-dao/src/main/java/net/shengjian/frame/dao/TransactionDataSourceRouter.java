package net.shengjian.frame.dao;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 配合spirng的事务拦截器,如果有事务就是写库,没有事务就是读库
 *
 * @author caomei
 */
public class TransactionDataSourceRouter extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        //不能判断当前是否有事务,因为事务是基于Connection的,如果有连接了,就意味着已经有dataSource了
        //需要写个切面,设置一个ThreadLocal,在获取DataSource之前,把读写标识放到ThreadLocal,这里取值判断
        if (FrameDataSourceTransactionManager.isExistTransaction()) {
            return "dataSourceWrite";
        }
        return "dataSourceRead";
    }

}
