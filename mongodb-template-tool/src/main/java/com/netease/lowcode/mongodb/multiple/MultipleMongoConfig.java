package com.netease.lowcode.mongodb.multiple;//package com.netease.lowcode.mongodb.multiple;
//
//import com.mongodb.MongoClient;
//import com.mongodb.MongoClientURI;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.mongo.MongoProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.mongodb.MongoDbFactory;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
//
//@Configuration
//public class MultipleMongoConfig {
//
//    @Autowired
//    private MultipleMongoProperties mongoProperties;
//
//    /**
//     * 利⽤构建好的 MongoDbFactory 创建对应的 MongoTemplate
//     *
//     * @return
//     * @throws Exception
//     */
//    @Primary
//    @Bean(name = "primaryMongoTemplate")
//    public MongoTemplate primaryMongoTemplate() throws Exception {
//        return new MongoTemplate(primaryFactory(this.mongoProperties.getPrimary()));
//    }
//
//    @Bean
//    @Qualifier("secondaryMongoTemplate")
//    public MongoTemplate secondaryMongoTemplate() throws Exception {
//        return new MongoTemplate(secondaryFactory(this.mongoProperties.getSecondary()));
//    }
//
//    /**
//     * 根据配置⽂件信息构建第⼀个数据源的 MongoDbFactory
//     *
//     * @param mongo
//     * @return
//     */
//    @Bean
//    @Primary
//    public MongoDbFactory primaryFactory(MongoProperties mongo) {
//        MongoClient client = new MongoClient(new MongoClientURI(mongo.getUri()));
//        return new SimpleMongoDbFactory(client, mongo.getDatabase());
//    }
//
//
//    @Bean
//    public MongoDbFactory secondaryFactory(MongoProperties mongo) {
//        MongoClient client = new MongoClient(new MongoClientURI(mongo.getUri()));
//        return new SimpleMongoDbFactory(client, mongo.getDatabase());
//    }
//}
