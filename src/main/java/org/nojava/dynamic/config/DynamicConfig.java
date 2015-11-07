package org.nojava.dynamic.config;

import java.util.UUID;

/**
 * User: cailei
 * Date: 15/7/13
 * Time: 下午1:48
 */
public class DynamicConfig {



    /**获取超时设置,单位秒**/
    private Long waitTime=100l;
    /** 识别热数据，新链接创建倍数,暂未实现 **/
    private int  hotTenatIdMultipleNum=1;
    /** 队列持有最大链接数 **/
    private int maxConnction=20;


    /** 队列最小闲置数量 **/
    private int minIdleConnction=1;

    /**闲置时间**/
    private Long idleMaxAgeInSeconds=120l;

    /** 扫描检查队列间隔时间 太小影响性能**/
    private Long checkIntervalTime=120l*3;

    private boolean defaultAutoCommit=true;

    /**使用者服务名，便于统计信息**/
    private String  useServiceName= UUID.randomUUID().toString();

    /** 收集统计信息时间 **/
    private Long collectTime=1000*100l;

    public Long getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Long collectTime) {
        this.collectTime = collectTime;
    }

    public String getUseServiceName() {
        return useServiceName;
    }

    public void setUseServiceName(String useServiceName) {
        this.useServiceName = useServiceName;
    }

    public Long getCheckIntervalTime() {
        return checkIntervalTime;
    }

    public void setCheckIntervalTime(Long checkIntervalTime) {
        this.checkIntervalTime = checkIntervalTime;
    }



    public boolean isDefaultAutoCommit() {
        return defaultAutoCommit;
    }

    public void setDefaultAutoCommit(boolean defaultAutoCommit) {
        this.defaultAutoCommit = defaultAutoCommit;
    }

    public int getHotTenatIdMultipleNum() {
        return hotTenatIdMultipleNum;
    }

    public void setHotTenatIdMultipleNum(int hotTenatIdMultipleNum) {
        this.hotTenatIdMultipleNum = hotTenatIdMultipleNum;
    }

    public Long getIdleMaxAgeInSeconds() {
        return idleMaxAgeInSeconds;
    }

    public void setIdleMaxAgeInSeconds(Long idleMaxAgeInSeconds) {
        this.idleMaxAgeInSeconds = idleMaxAgeInSeconds;
    }

    public int getMaxConnction() {
        return maxConnction;
    }

    public void setMaxConnction(int maxConnction) {
        this.maxConnction = maxConnction;
    }

    public int getMinIdleConnction() {
        return minIdleConnction;
    }

    public void setMinIdleConnction(int minIdleConnction) {
        this.minIdleConnction = minIdleConnction;
    }

    public Long getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(Long waitTime) {
        this.waitTime = waitTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DynamicConfig)) return false;

        DynamicConfig that = (DynamicConfig) o;

        if (hotTenatIdMultipleNum != that.hotTenatIdMultipleNum) return false;
        if (maxConnction != that.maxConnction) return false;
        if (minIdleConnction != that.minIdleConnction) return false;
        if (defaultAutoCommit != that.defaultAutoCommit) return false;
        if (waitTime != null ? !waitTime.equals(that.waitTime) : that.waitTime != null) return false;
        if (idleMaxAgeInSeconds != null ? !idleMaxAgeInSeconds.equals(that.idleMaxAgeInSeconds) : that.idleMaxAgeInSeconds != null)
            return false;
        if (checkIntervalTime != null ? !checkIntervalTime.equals(that.checkIntervalTime) : that.checkIntervalTime != null)
            return false;
        if (useServiceName != null ? !useServiceName.equals(that.useServiceName) : that.useServiceName != null)
            return false;
        return !(collectTime != null ? !collectTime.equals(that.collectTime) : that.collectTime != null);

    }

    @Override
    public int hashCode() {
        int result = waitTime != null ? waitTime.hashCode() : 0;
        result = 31 * result + hotTenatIdMultipleNum;
        result = 31 * result + maxConnction;
        result = 31 * result + minIdleConnction;
        result = 31 * result + (idleMaxAgeInSeconds != null ? idleMaxAgeInSeconds.hashCode() : 0);
        result = 31 * result + (checkIntervalTime != null ? checkIntervalTime.hashCode() : 0);
        result = 31 * result + (defaultAutoCommit ? 1 : 0);
        result = 31 * result + (useServiceName != null ? useServiceName.hashCode() : 0);
        result = 31 * result + (collectTime != null ? collectTime.hashCode() : 0);
        return result;
    }
}
