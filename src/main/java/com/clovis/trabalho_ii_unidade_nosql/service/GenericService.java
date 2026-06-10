package com.clovis.trabalho_ii_unidade_nosql.service;

import com.clovis.trabalho_ii_unidade_nosql.exception.ResourceNotFoundException;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenericService {

    private static final Logger log = LoggerFactory.getLogger(GenericService.class);
    private final MongoTemplate mongoTemplate;

    public List<Document> findAll(String collection, Integer page, Integer limit, String queryParam, String fields) {
        Query query;

        if (queryParam != null) {
            try {
                query = new BasicQuery(queryParam);
            } catch (Exception e) {
                throw new IllegalArgumentException("Parâmetro 'query' inválido: JSON mal formado.");
            }
        } else {
            query = new Query();
        }

        if (page != null && limit != null) {
            query.with(PageRequest.of(page, limit));
        }

        if (fields != null) {
            String[] fieldList = fields.split(",");
            for (String field : fieldList) {
                field = field.trim();
                if (field.startsWith("-")) {
                    query.fields().exclude(field.substring(1));
                } else {
                    query.fields().include(field);
                }
            }
        }

        return mongoTemplate.find(query, Document.class, collection);
    }

    public Document findById(String id, String collection) {
        Document doc = mongoTemplate.findById(id, Document.class, collection);
        if (doc == null) {
            throw new ResourceNotFoundException("Documento não encontrado na coleção '" + collection + "' com ID: " + id);
        }
        return doc;
    }

    public void save(Document document, String collection) {
        mongoTemplate.save(document, collection);
        log.info("Documento salvo na coleção '{}'", collection);
    }

    public void delete(String id, String collection) {
        Query query = new Query(Criteria.where("_id").is(id));
        DeleteResult result = mongoTemplate.remove(query, collection);

        if (result.getDeletedCount() == 0) {
            throw new ResourceNotFoundException(
                    "Não foi possível deletar: documento com ID '" + id + "' não encontrado na coleção '" + collection + "'."
            );
        }

        log.info("Documento com ID '{}' deletado da coleção '{}'", id, collection);
    }

    public void update(String id, String collection, Document document) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = Update.fromDocument(document);
        UpdateResult result = mongoTemplate.updateFirst(query, update, collection);

        if (result.getMatchedCount() == 0) {
            throw new ResourceNotFoundException(
                    "Não foi possível atualizar: documento com ID '" + id + "' não existe na coleção '" + collection + "'."
            );
        }

        if (result.getModifiedCount() == 0) {
            log.info("Documento com ID '{}' encontrado na coleção '{}', mas nenhum campo foi alterado.", id, collection);
        } else {
            log.info("Documento com ID '{}' atualizado na coleção '{}'", id, collection);
        }
    }
}