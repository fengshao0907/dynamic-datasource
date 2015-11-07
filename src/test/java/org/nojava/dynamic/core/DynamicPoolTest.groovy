package org.nojava.dynamic.core

import org.nojava.dynamic.config.DynamicConfig
import spock.lang.Specification

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.util.concurrent.CountDownLatch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
/**
 * User: cailei
 * Date: 15/11/6
 * Time: 下午8:36
 */
class DynamicPoolTest extends Specification{

    ConnectionHandle connection;
    DynamicPool dynamicPool;

    def "简单使用前后统计正确，并且在时间到了后回收"(){
        DynamicConfig dynamicConfig=new DynamicConfig();
        dynamicConfig.setIdleMaxAgeInSeconds(0l);
        dynamicConfig.checkIntervalTime=10;
        dynamicConfig.setCollectTime(10);
        dynamicPool=new DynamicPool(dynamicConfig,StubConnectionInfoConfig.stubMysqlDriverLocalhostNumberKey())
        def key=1.toString()
        connection=dynamicPool.getConnection(key)
        PreparedStatement preparedStatement=connection.prepareStatement("select * from config");
        ResultSet resultSet=preparedStatement.executeQuery()
        resultSet.next();
        resultSet.getString("prop_desc");
        expect:
        0== dynamicPool.queueConnections.get(key).intValue();
        1== dynamicPool.createdConnections.get(key).intValue();
        connection.getBorrowTime()>0l&&connection.getReturnTime()==-1l
        dynamicPool.close(connection);
        1== dynamicPool.createdConnections.get(key).intValue();
        1== dynamicPool.createdConnections.get(key).intValue();
        connection.getBorrowTime()==-1l&&connection.getReturnTime()>0l
        Thread.sleep(10*1000);
        0== dynamicPool.createdConnections.get(key).intValue();
        0== dynamicPool.queueConnections.get(key).intValue();
        0==dynamicPool.getConnectionHandleQueue().get(key).size();
    }




    def "300线程访问，连接池参数依正确"(){
        DynamicConfig dynamicConfig=new DynamicConfig();
        dynamicConfig.idleMaxAgeInSeconds=10;
        dynamicConfig.collectTime=10;
        dynamicConfig.checkIntervalTime=100;
        dynamicConfig.waitTime=2;
        dynamicPool=new DynamicPool(dynamicConfig,StubConnectionInfoConfig.stubMysqlDriverLocalhostNumberKey())
        CountDownLatch countDownLatch=new CountDownLatch(300);
        def keys=[]
        Thread mockVisitDb=new Thread(new Runnable() {
            @Override
            void run() {
                String key=new Random().nextInt(20);
                keys+=key.toString();
                ConnectionHandle connection=dynamicPool.getConnection(key)
                PreparedStatement preparedStatement=connection.prepareStatement("select * from config");
                ResultSet resultSet=preparedStatement.executeQuery()
                resultSet.next();
                resultSet.getString("prop_desc");
               // Thread.sleep(1000);
                preparedStatement.close();
                println "ps close"
                dynamicPool.close(connection)
                println "over"
                countDownLatch.countDown();
                println "当前数量："+countDownLatch.getCount();
            }
        });
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        300.times {
            threadPool.submit(mockVisitDb);
        }
        expect:
        countDownLatch.await()
        println "执行完成－－－－－－－－－－－－－－－－－－－"
        Thread.sleep(1000*100);
        keys.each {
            0== dynamicPool.createdConnections.get(it).intValue();
            0== dynamicPool.queueConnections.get(it).intValue();
            0==dynamicPool.getConnectionHandleQueue().get(it).size();
        }

        threadPool.shutdown();
    }






}

