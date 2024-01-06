package com.example.retolitethinking1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<String> handlerException(Exception ex){
        logger.error("Se produjo un error no controlado a nivel de servidor",ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("EGVM Server: Error interno del servidor, por favor revisa que la información enviada este correcta");
    }
}
