package org.nojava.dynamic.util;

import org.nojava.dynamic.core.DynamicPool;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.AbstractDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * User: cailei
 * Date: 15/8/13
 * Time: 上午11:31
 */
public class JdbcTemplateDynamic implements ApplicationContextAware {

    private  ApplicationContext applicationContext;

    public JdbcTemplate getJdbcTemplate(final String tentId, final DynamicPool dynamicPool){

        String beanName=tentId+"JdbcTemplate";
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext)applicationContext;
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
        if(beanFactory.containsBean(beanName)){
            return (JdbcTemplate) beanFactory.getBean(beanName);
        }
        DataSource dataSource=new AbstractDataSource() {
            @Override
            public Connection getConnection() throws SQLException {

                try {
                    return dynamicPool.getConnection(tentId);
                } catch (Exception e) {
                    logger.error("获取链接错误",e);
                    throw new SQLException("获取链接错误");
                }

            }
            @Override
            public Connection getConnection(String username, String password) throws SQLException {
                throw  new IllegalAccessError("错误进入，不支持此方法");
            }
        };
        BeanDefinitionBuilder beanDefinitionBuilder= BeanDefinitionBuilder.rootBeanDefinition(JdbcTemplate.class);
        beanDefinitionBuilder.addConstructorArgValue(dataSource);
        beanFactory.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        return (JdbcTemplate) applicationContext.getBean(beanName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
