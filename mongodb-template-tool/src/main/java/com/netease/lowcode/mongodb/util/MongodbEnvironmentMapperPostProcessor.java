package com.netease.lowcode.mongodb.util;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Order
public class MongodbEnvironmentMapperPostProcessor implements EnvironmentPostProcessor {
    private static final Map<String, String> MONGODB_CONFIG_MAPPER = new HashMap<>();

    static {
        MONGODB_CONFIG_MAPPER.put("extensions.mongodb_template_tool.custom.mongoDBUri", "spring.data.mongodb.uri");
        MONGODB_CONFIG_MAPPER.put("extensions.mongodb_template_tool.custom.mongoDBHost", "spring.data.mongodb.host");
        MONGODB_CONFIG_MAPPER.put("extensions.mongodb_template_tool.custom.mongoDBPort", "spring.data.mongodb.port");
        MONGODB_CONFIG_MAPPER.put("extensions.mongodb_template_tool.custom.mongoDBDataBase", "spring.data.mongodb.database");
        MONGODB_CONFIG_MAPPER.put("extensions.mongodb_template_tool.custom.mongoDBUserName", "spring.data.mongodb.username");
        MONGODB_CONFIG_MAPPER.put("extensions.mongodb_template_tool.custom.mongoDBPassWord", "spring.data.mongodb.password");
    }

    /**
     * Post-process the given {@code environment}.
     *
     * @param environment the environment to post-process
     * @param application the application to which the environment belongs
     */
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Map<String, Object> mapperProperties = new HashMap<>();
        MONGODB_CONFIG_MAPPER.forEach((key, value) -> {
            if (environment.containsProperty(value)) {
                return;
            }
            if (environment.containsProperty(key)) {
                String property = environment.getProperty(key);
                if (!StringUtils.isEmpty(property)) {
                    mapperProperties.put(value, property);
                }
            }
        });

        if (mapperProperties.isEmpty()) {
            return;
        }
        environment.getPropertySources().addLast(new MapPropertySource("MONGODB_CONFIG_MAPPER", mapperProperties));
    }

}