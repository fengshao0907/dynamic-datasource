package org.nojava.dynamic.core

import com.google.common.collect.Maps
import org.nojava.dynamic.config.ConnectionInfo

/**
 * User: cailei
 * Date: 15/11/7
 * Time: 下午1:08
 */
class StubConnectionInfoConfig {

            static def stubMysqlDriverLocalhostNumberKey(){
                 Map<String, ConnectionInfo> connectionInfoConfig= Maps.newConcurrentMap();
                 for(int i=0;i<20;i++){
                     ConnectionInfo connectionInfo=new ConnectionInfo()
                     connectionInfo.driverClassName="com.mysql.jdbc.Driver"
                     connectionInfo.username="cl"
                     connectionInfo.password="cl123456"
                     connectionInfo.tenantId=i.toString()
                     connectionInfo.url="jdbc:mysql://localhost:3306/ccms6_cl_02?useUnicode=true&characterEncoding=utf8&autoReconnect=true&allowMultiQueries=true"
                     connectionInfoConfig.put(i.toString(),connectionInfo);
                 }
                 return  connectionInfoConfig;
             }



}
