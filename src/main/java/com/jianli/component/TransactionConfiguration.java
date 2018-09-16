package com.jianli.component;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.CompositeTransactionAttributeSource;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author chendurex
 * @description
 * @date 2018-08-19 18:36
 */
@Configuration
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
@ConditionalOnBean(DataSource.class)
@EnableTransactionManagement(proxyTargetClass = true)
public class TransactionConfiguration {

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public TransactionInterceptor transactionInterceptor(DataSourceTransactionManager dataSourceTransactionManager) {
        Properties defaultTransactionAttr = new Properties();
        defaultTransactionAttr.setProperty("select*", "PROPAGATION_NOT_SUPPORTED, readOnly");
        defaultTransactionAttr.setProperty("query*", "PROPAGATION_NOT_SUPPORTED, readOnly");
        defaultTransactionAttr.setProperty("find*", "PROPAGATION_NOT_SUPPORTED, readOnly");
        defaultTransactionAttr.setProperty("list*", "PROPAGATION_NOT_SUPPORTED, readOnly");
        defaultTransactionAttr.setProperty("submit*", "PROPAGATION_REQUIRED, -Exception");
        defaultTransactionAttr.setProperty("modify*", "PROPAGATION_REQUIRED, -Exception");
        defaultTransactionAttr.setProperty("remove*", "PROPAGATION_REQUIRED, -Exception");
        defaultTransactionAttr.setProperty("*", "PROPAGATION_REQUIRED, -Exception");
        NameMatchTransactionAttributeSource tas = new NameMatchTransactionAttributeSource();
        tas.setProperties(defaultTransactionAttr);
        return new TransactionInterceptor(dataSourceTransactionManager,
                new CompositeTransactionAttributeSource(new TransactionAttributeSource[]{new AnnotationTransactionAttributeSource(), tas}));
    }

    @Bean
    public Advisor defaultPointcutAdvisor(TransactionInterceptor transactionInterceptor) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.jianli.service.impl.*.*(..))");
        return new DefaultPointcutAdvisor(pointcut, transactionInterceptor);
    }
}
