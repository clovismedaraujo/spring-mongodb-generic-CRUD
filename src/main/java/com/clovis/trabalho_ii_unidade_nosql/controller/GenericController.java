package com.clovis.trabalho_ii_unidade_nosql.controller;


import com.clovis.trabalho_ii_unidade_nosql.service.GenericService;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class GenericController {

    private final GenericService genericService;


    @GetMapping("/{collection}")
    public List<Document> findAll(@PathVariable String collection) {
        return genericService.findAll(collection);
    }

    @GetMapping("/{collection}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Document findById(@PathVariable String id,@PathVariable String collection) {
        return genericService.findById(id, collection);
    }

    @PostMapping("/{collection}")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Document document, @PathVariable String collection){
        genericService.save(document, collection);
    }

    @DeleteMapping("/{collection}/{id}")
    public void delete(@PathVariable String id,@PathVariable String collection){
        genericService.delete(id, collection);
    }

    @PutMapping("/{collection}/{id}")
    public void update(@PathVariable String id,@PathVariable String collection, @RequestBody Document document){
        genericService.update(id, collection, document);
    }

}
