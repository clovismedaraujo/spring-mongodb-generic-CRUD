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
    @ResponseStatus(HttpStatus.OK)
    public List<Document> findAll(
        @PathVariable String collection,
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "10") Integer limit,
        @RequestParam(required = false) String query,
        @RequestParam(required = false) String fields
    ){
        return genericService.findAll(collection, page, limit, query, fields);
    }

    @GetMapping("/{collection}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Document findById(@PathVariable String id, @PathVariable String collection) {
        return genericService.findById(id, collection);
    }

    @PostMapping("/{collection}")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Document document, @PathVariable String collection){
        genericService.save(document, collection);
    }

    @DeleteMapping("/{collection}/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id,@PathVariable String collection){
        genericService.delete(id, collection);
    }

    @PutMapping("/{collection}/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String id,@PathVariable String collection, @RequestBody Document document){
        genericService.update(id, collection, document);
    }

}
