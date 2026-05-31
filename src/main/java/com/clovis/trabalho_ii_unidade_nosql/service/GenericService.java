package com.clovis.trabalho_ii_unidade_nosql.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.bson.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenericService {

    private final MongoTemplate mongoTemplate;

    public List<Document> findAll(String collection) {
        return mongoTemplate.findAll(Document.class, collection);
    }

    public Document findById(String id, String collection){
        return mongoTemplate.findById(id, Document.class, collection);
    }

    public void save(Document document, String collection){
        mongoTemplate.save(document, collection);
    }

    public void delete(String id, String collection){
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, collection);
    }

    public void update(String id, String collection, Document document){
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = Update.fromDocument(document);
        mongoTemplate.updateFirst(query, update, collection);
    }
}

