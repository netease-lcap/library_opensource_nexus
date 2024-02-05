package com.netease.lowcode.mongodb.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.netease.lowcode.core.annotation.NaslLogic;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
public class MongoDbTool {

    public static MongoTemplate mongoTemplate;

    @NaslLogic
    public static String findByKey(String collName, Map<String, String> keyValue) throws JsonProcessingException {
        Query query = new Query();
        keyValue.forEach((key, value) -> query.addCriteria(Criteria.where(key).is(value)));
        FindIterable<Document> iterable = mongoTemplate.getCollection(collName).find(query.getQueryObject());
        List<Document> documents = new ArrayList<>();
        for (Document doc : iterable) {
            documents.add(doc);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(documents);
    }

    @NaslLogic
    public static Boolean insertOne(String collName, String data) {
        try {
            Document document = null;
            try {
                data = convertToJsonString(data);
                document = Document.parse(data);
            } catch (Exception e) {
                throw e;
            }
            mongoTemplate.getCollection(collName).insertOne(document);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    public static String convertToJsonString(String input) {
        return input.replace("\\", "");
    }

    /**
     * 更新集合中符合条件的一条数据
     *
     * @param collName 集合名
     * @param query    查询条件，JSON 字符串格式
     * @param update   需要更新的字段，JSON 字符串格式
     * @return 是否更新成功
     * @throws Exception 更新数据异常
     */
    @NaslLogic
    public static Boolean updateOne(String collName, String query, String update) {
        try {
            query = convertToJsonString(query);
            update = convertToJsonString(update);
            Document queryDoc = Document.parse(query);
            Document updateDoc = Document.parse(update);
            mongoTemplate.getCollection(collName).replaceOne(queryDoc, updateDoc);
            UpdateResult result = mongoTemplate.getCollection(collName).updateOne(queryDoc, updateDoc);
            return result.getModifiedCount() > 0;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 删除集合中符合条件的一条数据
     *
     * @param collName 集合名
     * @param query    查询条件，JSON 字符串格式
     * @return 是否删除成功
     * @throws Exception 删除数据异常
     */
    @NaslLogic
    public static Boolean deleteOne(String collName, String query) {
        try {
            query = convertToJsonString(query);
            Document queryDoc = Document.parse(query);
            DeleteResult result = mongoTemplate.getCollection(collName).deleteOne(queryDoc);
            return result.getDeletedCount() > 0;
        } catch (Exception e) {
            throw e;
        }
    }

    @NaslLogic
    public static Boolean deleteMany(String collName, String query) {
        try {
            query = convertToJsonString(query);
            Document queryDoc = Document.parse(query);
            DeleteResult result = mongoTemplate.getCollection(collName).deleteMany(queryDoc);
            return result.getDeletedCount() > 0;
        } catch (Exception e) {
            throw e;
        }
    }

    @NaslLogic
    public static String find(String collName, String query) {
        try {
            query = convertToJsonString(query);
            Document queryDoc = Document.parse(query);
            MongoCollection<Document> collection = mongoTemplate.getCollection(collName);
            FindIterable<Document> iterable = collection.find(queryDoc);
            List<Document> documents = new ArrayList<>();
            for (Document doc : iterable) {
                documents.add(doc);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(documents);
        } catch (Exception e) {
            throw new RuntimeException("查询数据异常", e);
        }
    }

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) throws Exception {

        MongoDbTool.mongoTemplate = mongoTemplate;

    }

}
