package org.nojava.dynamic.util;

import org.apache.commons.lang3.StringUtils;
import org.nojava.dynamic.core.DynamicPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.datasource.AbstractDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * User: cailei
 * Date: 15/8/14
 * Time: 下午5:33
 */
public class RoutingDataSource extends AbstractDataSource implements InitializingBean {

    /** Logger class */
    private static final Logger logger = LoggerFactory.getLogger(RoutingDataSource.class);


    private DynamicPool dynamicPool;



    public void setDynamicPool(DynamicPool dynamicPool) {
        this.dynamicPool = dynamicPool;
    }

    public RoutingDataSource(DynamicPool dynamicPool) {
        this.dynamicPool = dynamicPool;
    }

    @Override
    public Connection getConnection() throws SQLException {
        try {
            if(StringUtils.isNotBlank(TenantIdContextHolder.getTenantId())){
                return dynamicPool.getConnection(TenantIdContextHolder.getTenantId());
            }
        } catch (Exception e) {
           logger.error("获取动态数据源连接出错",e);
        }
        throw  new SQLException("获取动态数据源连接出错，租户id为:",TenantIdContextHolder.getTenantId());
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        throw  new IllegalAccessError("不支持该方法调用");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (this.dynamicPool == null) {
            throw new IllegalArgumentException(" dynamicPool is required");
        }
    }
}
