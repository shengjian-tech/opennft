package net.shengjian.frame.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.RMClient;
import io.seata.rm.datasource.DataSourceProxy;
import io.seata.tm.TMClient;
import net.shengjian.frame.util.GlobalStatic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 数据库配置和seata配置,使用@GlobalTransactional注解
 *
 * @author caomei
 */
@Configuration("configuration-DataSourceConfig")
public class DataSourceConfig {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    private Integer initialSize = 50;
    private Integer minIdle = 50;
    private Integer maxActive = 200;
    private Long maxWaitMillis = 60000L;
    private Long minEvictableIdleTimeMillis = 35000L;
    private Long timeBetweenEvictionRunsMillis = 10000L;
    private Integer removeAbandonedTimeout = 1800;

    private Boolean removeAbandoned = true;
    private Boolean logAbandoned = true;
    private Boolean testWhileIdle = true;
    // 提升性能,暂时关闭testOnBorrow.还存在和mssql2015兼容的问题.
    private Boolean testOnBorrow = false;
    private Boolean testOnReturn = false;

    // private String filters="stat,wall,slf4j";
    // private String
    // connectionProperties="druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000";
    private String filters = "stat";
    private String connectionProperties = "druid.stat.slowSqlMillis=1000;druid.stat.logSlowSql=true";

    /**
     * 如果使用了spring扫描器,就要把datasource里的RMClient.init和TMClient.init注释掉,避免重复注册,也不用判断GlobalStatic.seataEnable了.
     * 解开org.springrain.rpc.grpcimpl.CommonGrpcService.commonHandle中有关GlobalStatic.seataSpringEnable判断的代码.
     *
     * @return
     */
    /**
     * @Bean("globalTransactionScanner") public GlobalTransactionScanner
     * globalTransactionScanner() { GlobalStatic.seataEnable = true;
     * GlobalStatic.seataSpringEnable = true; return new
     * GlobalTransactionScanner(GlobalStatic.seataApplicationId,
     * GlobalStatic.seataTransactionServiceGroup); }
     **/
    /**
     * 自定义 dataSource,用户扩展实现
     */
    @Bean("dataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);// 用户名
        dataSource.setPassword(password);// 密码
        // 设置属性
        setDataSourceProperties(dataSource);


        // 如果使用了globalTransactionScanner,就一定要使用DataSourceProxy
        // return new DataSourceProxy(dataSource);

        // 如果使用Seata
        if (GlobalStatic.seataEnable) {
            // 设置seata的datasource代理
            DataSourceProxy proxy = new DataSourceProxy(dataSource);
            // 初始化注册seata
            RMClient.init(GlobalStatic.seataApplicationId, GlobalStatic.seataTransactionServiceGroup);
            TMClient.init(GlobalStatic.seataApplicationId, GlobalStatic.seataTransactionServiceGroup);
            return proxy;
        }

        return dataSource;
    }

    /**
     * 根据事务隔离级别做读写分离,有事务就写,无事务就是读
     *
     * @return
     */
    /*
     * @Bean("dataSource") public DataSource dataSource1() {
     * TransactionDataSourceRouter dataSource = new TransactionDataSourceRouter();
     *
     * Map<Object, Object>targetDataSources=new HashMap<>();
     * targetDataSources.put("dataSourceWrite", dataSourceWrite);
     * targetDataSources.put("dataSourceRead", dataSourceRead);
     * dataSource.setTargetDataSources(targetDataSources); //默认读库
     * dataSource.setDefaultTargetDataSource(dataSourceRead); return dataSource; }
     */
    @Bean("jdbc")
    public NamedParameterJdbcTemplate jdbc() {
        NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(dataSource());
        return jdbc;
    }

    @Bean("jdbcCall")
    @Scope("prototype")
    public SimpleJdbcCall jdbcCall() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource());
        return jdbcCall;
    }

    private DruidDataSource setDataSourceProperties(DruidDataSource dataSource) {
        try {
            dataSource.setInitialSize(initialSize);
            dataSource.setMinIdle(minIdle);
            dataSource.setMaxActive(maxActive);
            dataSource.setMaxWait(maxWaitMillis);
            dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
            dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
            dataSource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
            dataSource.setRemoveAbandoned(removeAbandoned);
            dataSource.setLogAbandoned(logAbandoned);
            dataSource.setTestWhileIdle(testWhileIdle);
            dataSource.setTestOnBorrow(testOnBorrow);
            dataSource.setTestOnReturn(testOnReturn);

            dataSource.setFilters(filters);
            dataSource.setConnectionProperties(connectionProperties);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }

        return dataSource;

    }

}
