package com.br.mongo.Prova03;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCursor;
import java.util.Arrays;
import java.util.List;

public class MongoDBRedeSocial {

    public static void main(String[] args) {
    	MongoClient mongoClient = new MongoClient("localhost", 27017);
    	MongoDatabase database = mongoClient.getDatabase("RedeSocial");
        MongoCollection<Document> usuariosCollection = database.getCollection("Usuários");
        MongoCollection<Document> postagensCollection = database.getCollection("Postagens");

        // Inserção de 4 novos usuários
        Document usuario1 = new Document("Pedro", "Usuário1")
                .append("seguidores", 1000)
                .append("area_interesse", "Tecnologia");

        Document usuario2 = new Document("Gabriel", "Usuário2")
                .append("seguidores", 200)
                .append("area_interesse", "Marketing");

        Document usuario3 = new Document("Arthur", "Usuário3")
                .append("seguidores", 150)
                .append("area_interesse", "Odonto");

        Document usuario4 = new Document("Lara", "Usuário4")
                .append("seguidores", 300)
                .append("area_interesse", "Moda");

        usuariosCollection.insertMany(Arrays.asList(usuario1, usuario2, usuario3, usuario4));

        // Atualização do número de seguidores de um usuário (Usuário1)
        usuariosCollection.updateOne(Filters.eq("Pedro", "Usuário1"), Updates.set("seguidores", 3301));

        // Recuperação de usuários por interesse (Tecnologia)
        MongoCursor<Document> usuariosPorInteresse = usuariosCollection.find(Filters.eq("area_interesse", "Tecnologia")).iterator();
        while (usuariosPorInteresse.hasNext()) {
            System.out.println(usuariosPorInteresse.next());
        }

        // Agregação para calcular a média de seguidores por usuário
        List<Bson> pipeline = Arrays.asList(
            Aggregates.group(null, Accumulators.avg("media_seguidores", "$seguidores"))
        );

        AggregateIterable<Document> resultadoAgregacao = usuariosCollection.aggregate(pipeline);
        Document mediaSeguidores = resultadoAgregacao.first();
        System.out.println("Média de seguidores por usuário: " + mediaSeguidores.getDouble("media_seguidores"));

        // Inserção de 4 novas postagens
        Document postagem1 = new Document("conteudo1", "texto avaliativo")
                .append("tipo", "Texto")
                .append("curtidas", 50)
                .append("avaliacao", "Gostei da foto");

        Document postagem2 = new Document("conteudo2", "foto")
                .append("tipo", "Imagem")
                .append("curtidas", 75);

        Document postagem3 = new Document("conteudo3", "video de luta")
                .append("tipo", "Vídeo")
                .append("curtidas", 30);

        Document postagem4 = new Document("conteudo4", "sinopse")
                .append("tipo", "Texto")
                .append("curtidas", 60);


        postagensCollection.insertMany(Arrays.asList(postagem1, postagem2, postagem3, postagem4));

        // Atualização do tipo de uma postagem (Postagem 1)
        postagensCollection.updateOne(Filters.eq("conteudo1", "texto avaliativo"), Updates.set("tipo", "Link"));

        // Recuperação de postagens de tipos iguais (Texto)
        MongoCursor<Document> postagensDoTipoTexto = postagensCollection.find(Filters.eq("tipo", "Texto")).iterator();
        while (postagensDoTipoTexto.hasNext()) {
            System.out.println(postagensDoTipoTexto.next());
        }

        // Agregação para contar a quantidade de curtidas por postagem
        List<Bson> pipelinePostagens = Arrays.asList(
            Aggregates.group("$conteudo", Accumulators.sum("total_curtidas", "$curtidas"))
        );

        AggregateIterable<Document> resultadoAgregacaoPostagens = postagensCollection.aggregate(pipelinePostagens);
        for (Document postagem : resultadoAgregacaoPostagens) {
            System.out.println("Postagem: " + postagem.getString("_id") + ", Total de Curtidas: " + postagem.getInteger("total_curtidas"));
        }

        mongoClient.close();
    }
}
