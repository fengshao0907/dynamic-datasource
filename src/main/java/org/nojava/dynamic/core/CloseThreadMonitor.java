package org.nojava.dynamic.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: cailei
 * Date: 15/7/13
 * Time: 下午8:17
 *
 *
 * 目前这种方式阻塞有待改进，需要设置intervalTime稍微长点
 */
public class CloseThreadMonitor implements Runnable{

    /** Logger class */
    private static final Logger logger = LoggerFactory.getLogger(CloseThreadMonitor.class);


    /** queue connections  */
    private DynamicPool dynamicPool;


    public CloseThreadMonitor(DynamicPool dynamicPool) {
        this.dynamicPool = dynamicPool;

    }

    @Override
    public void run() {
        while (true){
            try{
                Thread.sleep(dynamicPool.getDynamicConfig().getCheckIntervalTime()*1000);
                dynamicPool.cleanIdleConn();
            }catch (Throwable t){
                logger.error("监控关闭线程异常，队列某些线程无法关闭",t);
            }
        }

    }


}
