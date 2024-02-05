package com.netease.lowcode.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import com.netease.lowcode.mongodb.multiple.MultipleMongoProperties;
//@EnableConfigurationProperties(MultipleMongoProperties.class)

@SpringBootApplication
public class MongodbTemplateToolApplication {
    public static void main(String[] args) {
        SpringApplication.run(MongodbTemplateToolApplication.class, args);
    }
}
