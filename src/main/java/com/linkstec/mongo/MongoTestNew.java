package com.linkstec.mongo;

import java.net.UnknownHostException;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;  
  
public class MongoTestNew {  
  
    public static void main(String[] args) throws UnknownHostException {  
          
        /** 
         * Mongo类代表与MongoDB服务器的连接，有多种构造函数。无参构造函数默认连接localhost:27017. 
         */  
    	MongoClient mongoClient = new MongoClient("localhost:27017");  
        /** 
         * DB类代表数据库，如果当前服务器上没有该数据库，会默认创建一个 
         */  
    	MongoDatabase db = mongoClient.getDatabase("mylearndb");  
        /** 
         * DBCollection代表集合，如果数据库中没有该集合，会默认创建一个 
         */  
    	MongoCollection<Document> users = db.getCollection("users");  
        /** 
         * DBObject代表文档，这是一个接口，java中提供了多种实现，最简单的就是BasicDBObject了 
         */  
    	Document user = new Document();  
        user.put("name", "jimmy");  
        user.put("age", "34");
        user.put("工作", "程序员");
        Document address = new Document();
        address.put("city", "bj");
        address.put("street", "bq road");
        address.put("mail", "ufpark 68#");
        /**
         * 对于内嵌文档，我们需要先将内嵌文档填充后，再填充到外层文档中
         */
        user.put("address", address);
        // 将该文档插入到集合中
        users.insertOne(user);
        // 从集合中查询数据，我们就查询一条，调用findOne即可
        for (Document document : users.find()) {
            System.out.println(document);
        }
        mongoClient.close();
    }
}