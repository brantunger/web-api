package com.dreadfallguild.webapi.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResourceException extends RuntimeException {
    private HttpStatus httpStatus;

    public ResourceException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
