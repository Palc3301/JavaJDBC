package com.br.mongo.Etapa03;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBExample {
    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("NomeDaBaseDeDados");
        MongoCollection<Document> collection = database.getCollection("NomeDaColecao");


        Document document = new Document()
                .append("nome", "Exemplo")
                .append("idade", 30)
                .append("cargo", "Desenvolvedor")
                .append("cidade", "São Paulo");


        collection.insertOne(document);

        mongoClient.close();
    }
}
