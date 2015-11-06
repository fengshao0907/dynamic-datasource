package org.nojava.dynamic.state;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: cailei
 * Date: 15/7/17
 * Time: 下午10:35
 */
public class DynamicConnectionState {

    /**使用者服务名，便于统计信息**/
    private String  useServiceName;
    /** 运行动态识别码，集群识别 **/
    private  String uuid= UUID.randomUUID().toString();

    private ConcurrentHashMap<String, AtomicInteger> createdConnections = new ConcurrentHashMap<>();

    private ConcurrentHashMap<String, AtomicInteger> queueConnections = new ConcurrentHashMap<>();
    /**收集时间**/
    private Long currentTime;

    public ConcurrentHashMap<String, AtomicInteger> getCreatedConnections() {
        return createdConnections;
    }

    public void setCreatedConnections(ConcurrentHashMap<String, AtomicInteger> createdConnections) {
        this.createdConnections = createdConnections;
    }



    public Long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Long currentTime) {
        this.currentTime = currentTime;
    }

    public ConcurrentHashMap<String, AtomicInteger> getQueueConnections() {
        return queueConnections;
    }

    public void setQueueConnections(ConcurrentHashMap<String, AtomicInteger> queueConnections) {
        this.queueConnections = queueConnections;
    }

    public String getUseServiceName() {
        return useServiceName;
    }

    public void setUseServiceName(String useServiceName) {
        this.useServiceName = useServiceName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    @Override
    public String toString() {
        return "DynamicConnectionState{" +
                "createdConnections=" + createdConnections+
                ", useServiceName='" + useServiceName + '\'' +
                ", uuid='" + uuid + '\'' +
                ", queueConnections=" + queueConnections +
                ", currentTime=" + currentTime +
                '}';
    }


}
