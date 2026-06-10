package com.clovis.trabalho_ii_unidade_nosql.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<HandlerMessage> resourceNotFoundHandler(
            ResourceNotFoundException exception,
            HttpServletRequest request) {

        log.warn("Recurso não encontrado em {}: {}", request.getRequestURI(), exception.getMessage());

        HandlerMessage handlerMessage = new HandlerMessage(
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(handlerMessage);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<HandlerMessage> handleIllegalArgument(
            IllegalArgumentException ex,
            HttpServletRequest request) {

        log.warn("Argumento inválido em {}: {}", request.getRequestURI(), ex.getMessage());

        HandlerMessage handlerMessage = new HandlerMessage(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(handlerMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HandlerMessage> handleGeneralException(
            Exception ex,
            HttpServletRequest request) {

        log.error("Erro inesperado em {}: {}", request.getRequestURI(), ex.getMessage(), ex);

        HandlerMessage handlerMessage = new HandlerMessage(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro interno no servidor. Tente novamente mais tarde.",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(handlerMessage);
    }
}