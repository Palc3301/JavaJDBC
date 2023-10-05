package com.br.mongo.mongo;

import org.bson.codecs.configuration.CodecRegistries;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import model.Produto;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;

public class App 
{
    public static void main( String[] args ) {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
            MongoClient.getDefaultCodecRegistry(),
            CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );
        
        MongoClient mongoClient = new MongoClient("localhost:27017", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());

        MongoDatabase database = mongoClient.getDatabase("exemplo").withCodecRegistry(pojoCodecRegistry);
    
        MongoCollection<Produto> collection = database.getCollection("Produto", Produto.class);
        
    
    }
}
