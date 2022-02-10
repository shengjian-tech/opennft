package net.shengjian.frame.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 我也无无奈啊,使用了一个xml,没有找到javaconfig的方式.
 *
 * @author caomei
 */

@Configuration("configuration-TransactionConfig")
@ImportResource("classpath:applicationContext-tx.xml")
public class TransactionConfig {

    /*
     *
     * private static final String CUSTOMIZE_TRANSACTION_INTERCEPTOR_NAME =
     * "customizeTransactionInterceptor"; //可传播事务配置 private static final String[]
     * DEFAULT_REQUIRED_METHOD_RULE_TRANSACTION_ATTRIBUTES = { "save*",
     * "delete*","update*", };
     *
     * @Resource private DataSource dataSource;
     *
     *
     *
     * @Bean("transactionManager") public DataSourceTransactionManager
     * transactionManager() { DataSourceTransactionManager transactionManager = new
     * DataSourceTransactionManager(); transactionManager.setDataSource(dataSource);
     * return transactionManager; }
     *
     *
     * @Bean(CUSTOMIZE_TRANSACTION_INTERCEPTOR_NAME) public TransactionInterceptor
     * customizeTransactionInterceptor() { NameMatchTransactionAttributeSource
     * transactionAttributeSource = new NameMatchTransactionAttributeSource();
     * RuleBasedTransactionAttribute required = this.requiredTransactionRule();
     *
     * // 默认的传播事务配置 for (String methodName :
     * DEFAULT_REQUIRED_METHOD_RULE_TRANSACTION_ATTRIBUTES) {
     * transactionAttributeSource.addTransactionalMethod(methodName, required); }
     *
     * return new TransactionInterceptor(transactionManager(),
     * transactionAttributeSource); }
     *
     *
     *
     * //废弃方法,无法实现切面拦截 //@Bean public BeanNameAutoProxyCreator
     * customizeTransactionBeanNameAutoProxyCreator() { BeanNameAutoProxyCreator
     * beanNameAutoProxyCreator = new BeanNameAutoProxyCreator(); // 设置定制的事务拦截器
     * beanNameAutoProxyCreator.setInterceptorNames(
     * CUSTOMIZE_TRANSACTION_INTERCEPTOR_NAME);
     * beanNameAutoProxyCreator.setBeanNames("*Service,*ServiceImpl");
     * //beanNameAutoProxyCreator.setProxyTargetClass(true); return
     * beanNameAutoProxyCreator; }
     *
     *
     * // 基于CGLIB的实现,最好基于接口实现,这样混淆对接RPC都没有问题,标准就是标准啊!!!!!
     *
     * @Bean public Advisor txAdviceAdvisor() { JdkRegexpMethodPointcut pointcut =
     * new JdkRegexpMethodPointcut(); pointcut.setPattern(".*Service.*"); return new
     * DefaultPointcutAdvisor(pointcut, customizeTransactionInterceptor()); }
     *
     *
     *
     *
     * // 支持当前事务;如果不存在创建一个新的 private RuleBasedTransactionAttribute
     * requiredTransactionRule() { RuleBasedTransactionAttribute required = new
     * RuleBasedTransactionAttribute();
     * required.setRollbackRules(Collections.singletonList(new
     * RollbackRuleAttribute(Exception.class)));
     * required.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
     * required.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
     * required.setTimeout(TransactionDefinition.TIMEOUT_DEFAULT); return required;
     * }
     */

}
