package com.clovis.trabalho_ii_unidade_nosql.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.bson.Document;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenericService {

    private final MongoTemplate mongoTemplate;

    public List<Document> findAll(String collection, Integer page, Integer limit, String queryParam, String fields) {
        Query query;

        if (queryParam != null) {
            query = new BasicQuery(queryParam);
        } else {
            query = new Query();
        }

        if (page != null && limit != null) {
            query.with(PageRequest.of(page, limit));
        }

        if (fields != null) {
            String[] fieldList = fields.split(",");
            for (String field : fieldList) {
                if (field.startsWith("-")) {
                    query.fields().exclude(field.substring(1));
                } else {
                    query.fields().include(field);
                }
            }
        }

        return mongoTemplate.find(query, Document.class, collection);
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

