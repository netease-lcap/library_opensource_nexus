package com.netease.lowcode.mongodb.util;


import com.netease.lowcode.core.annotation.NaslConfiguration;
import org.springframework.beans.factory.annotation.Value;


public class MongoConfig {

    /**
     * MongoDB连接URI（优先级更高）
     */
    @NaslConfiguration
    @Value("${mongoDBUri}")
    private String mongoDBUri;
    /**
     * MongoDB主机名
     */
    @NaslConfiguration
    @Value("${mongoDBHost}")
    private String mongoDBHost;
    /**
     * MongoDB端口号
     */
    @NaslConfiguration
    @Value("${mongoDBPort}")
    private String mongoDBPort;
    /**
     * 要使用的数据库名称
     */
    @NaslConfiguration
    @Value("${mongoDBDataBase}")
    private String mongoDBDataBase;
    /**
     * 连接MongoDB的用户名
     */
    @NaslConfiguration
    @Value("${mongoDBUserName}")
    private String mongoDBUserName;
    /**
     * 连接MongoDB的密码
     */
    @NaslConfiguration
    @Value("${mongoDBPassWord}")
    private String mongoDBPassWord;
}
