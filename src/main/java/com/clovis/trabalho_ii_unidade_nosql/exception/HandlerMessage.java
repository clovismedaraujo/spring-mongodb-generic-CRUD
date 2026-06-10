package com.clovis.trabalho_ii_unidade_nosql.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class HandlerMessage {

    private HttpStatus status;
    private String message;
    private LocalDateTime timestamp;
    private String path;

    public HandlerMessage(HttpStatus status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }
}