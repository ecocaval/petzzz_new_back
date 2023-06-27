package com.app.petz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductSizesNotFoundException extends RuntimeException{
    public ProductSizesNotFoundException(String message){super(message);}
}
