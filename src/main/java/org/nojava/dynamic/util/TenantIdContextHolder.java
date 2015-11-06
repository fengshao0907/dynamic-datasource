package org.nojava.dynamic.util;

/**
 * User: cailei
 * Date: 15/8/14
 * Time: 下午4:18
 */
public class TenantIdContextHolder {
    private static final ThreadLocal<String> contextHolder =
            new ThreadLocal<String>();


    public static void setTenantId(String tenantId) {
        clearTenantId();
        contextHolder.set(tenantId);
    }

    public static String getTenantId() {
        return contextHolder.get();
    }

    private static void clearTenantId() {
        contextHolder.remove();
    }



}
