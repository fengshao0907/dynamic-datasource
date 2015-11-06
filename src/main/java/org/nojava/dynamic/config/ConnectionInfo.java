package org.nojava.dynamic.config;

/**
 * User: cailei
 * Date: 15/7/9
 * Time: 下午4:36
 */
public class ConnectionInfo {
    private String tenantId;
    private String password;
    private String username;
    private String url;
    private String driverClassName;

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ConnectionInfo{" +
                "driverClassName='" + driverClassName + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
