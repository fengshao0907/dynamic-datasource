package org.nojava.dynamic.core;

import org.nojava.dynamic.config.DynamicConfig;
import org.nojava.dynamic.state.DynamicConnectionState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: cailei
 * Date: 15/7/17
 * Time: 下午3:54
 */
public class   CollectThreadMonitor implements Runnable{


    /** Logger class. */
    private static final Logger logger = LoggerFactory.getLogger(CollectThreadMonitor.class);


    private DynamicConfig dynamicConfig;

    public void setDynamicConfig(DynamicConfig dynamicConfig) {
        this.dynamicConfig = dynamicConfig;
    }

    private DynamicPool dynamicPool;

    public CollectThreadMonitor(DynamicPool dynamicPool) {
        this.dynamicPool = dynamicPool;
        this.dynamicConfig=dynamicPool.getDynamicConfig();
    }

    private DynamicConnectionState buildDynamicConnectionState(){
        DynamicConnectionState dynamicConnectionState=new DynamicConnectionState();
        dynamicConnectionState.setCreatedConnections(dynamicPool.getCreatedConnections());
        dynamicConnectionState.setQueueConnections(dynamicPool.getQueueConnections());
        dynamicConnectionState.setUseServiceName(dynamicConfig.getUseServiceName());
        dynamicConnectionState.setCurrentTime(System.currentTimeMillis());
        return dynamicConnectionState;
    }


    @Override
    public void run() {
        while(true){
            try{
                Thread.sleep(dynamicConfig.getCollectTime());
                logger.info("连接池配置:"+dynamicConfig.toString());
                DynamicConnectionState dynamicConnectionState=buildDynamicConnectionState();
                logger.info("连接池现在状态："+dynamicConnectionState.toString());
            }   catch (Exception e){
                logger.error("收集信息线程发生异常",e);
                e.printStackTrace();
            }
        }
    }
}
