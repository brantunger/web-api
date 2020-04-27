package com.dreadfall.webapi.controller;

import com.dreadfall.webapi.exception.ResourceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(ResourceException.class)
    public ResponseEntity<String> handleException(ResourceException e) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(e.getMessage(), headers, e.getHttpStatus());
    }
}
