package com.example.springwebflux.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ObjectValidationException extends RuntimeException {

    public ObjectValidationException(String errorDetails) {
        super(errorDetails);
    }

}