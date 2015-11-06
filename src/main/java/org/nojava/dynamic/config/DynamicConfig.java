package org.nojava.dynamic.config;

/**
 * User: cailei
 * Date: 15/7/13
 * Time: 下午1:48
 */
public class DynamicConfig {



    /**获取超时设置,单位秒**/
    private Long waitTime=10000l;
    /** 识别热数据，新链接创建倍数 **/
    private int  hotTenatIdMultipleNum=1;
    /** 队列持有最大链接数 **/
    private int maxConnction=20;


    /** 队列最小闲置数量 **/
    private int minIdleConnction=0;

    /**闲置时间**/
    private Long idleMaxAgeInSeconds=120l;

    /** 扫描检查队列间隔时间 太小影响性能，推荐为idleMaxAgeInSeconds*3**/
    private Long checkIntervalTime=10*1000l;

    private boolean defaultAutoCommit=true;

    private Long dbConfigRefreshTime=3600*10l;

    /**使用者服务名，便于统计信息**/
    private String  useServiceName;

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

    public Long getDbConfigRefreshTime() {
        return dbConfigRefreshTime;
    }

    public void setDbConfigRefreshTime(Long dbConfigRefreshTime) {
        this.dbConfigRefreshTime = dbConfigRefreshTime;
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
        if (!waitTime.equals(that.waitTime)) return false;
        if (!idleMaxAgeInSeconds.equals(that.idleMaxAgeInSeconds)) return false;
        if (!checkIntervalTime.equals(that.checkIntervalTime)) return false;
        if (!dbConfigRefreshTime.equals(that.dbConfigRefreshTime)) return false;
        if (!useServiceName.equals(that.useServiceName)) return false;
        return collectTime.equals(that.collectTime);

    }

    @Override
    public int hashCode() {
        int result = waitTime.hashCode();
        result = 31 * result + hotTenatIdMultipleNum;
        result = 31 * result + maxConnction;
        result = 31 * result + minIdleConnction;
        result = 31 * result + idleMaxAgeInSeconds.hashCode();
        result = 31 * result + checkIntervalTime.hashCode();
        result = 31 * result + (defaultAutoCommit ? 1 : 0);
        result = 31 * result + dbConfigRefreshTime.hashCode();
        result = 31 * result + useServiceName.hashCode();
        result = 31 * result + collectTime.hashCode();
        return result;
    }
}
