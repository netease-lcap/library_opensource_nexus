package com.netease;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.netease.lowcode.mongodb.MongodbTemplateToolApplication;
import com.netease.lowcode.mongodb.repository.User;
import com.netease.lowcode.mongodb.repository.UserRepository;
import com.netease.lowcode.mongodb.util.MongoDbTool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = MongodbTemplateToolApplication.class)
@RunWith(SpringRunner.class)
public class CheckTest {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private MongoTemplate primaryMongoTemplate;
//    @Autowired
//    private MongoTemplate secondaryMongoTemplate;
//    @Autowired
//    private MultipleMongoProperties multipleMongoProperties;

    @Test
    public void queryDataTestRepository() {
        User user = new User();
        user.setAge(18);
        user.setUserId("123");
        user.setUserName("james");
        System.out.println(userRepository.insert(user));
    }

    @Test
    public void queryDataTest() {
        Map map = new HashMap<>();
        map.put("name", "james");
//        map.put("age", 24);
        try {
            System.out.println(MongoDbTool.findByKey("collection1", map));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
