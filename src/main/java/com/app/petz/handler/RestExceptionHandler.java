package com.app.petz.handler;

import com.app.petz.exception.PetNotFoundException;
import com.app.petz.exception.PetNotFoundExceptionDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Log4j2
public class RestExceptionHandler {
    @ExceptionHandler(PetNotFoundException.class)
    protected ResponseEntity<PetNotFoundExceptionDetails> handlePetNotFoundException(PetNotFoundException exception){
        return new ResponseEntity<>(
                PetNotFoundExceptionDetails.builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.NOT_FOUND.value())
                    .title(exception.getCause().getMessage())
                    .details(exception.getMessage())
                    .developerMessage(exception.getClass().getName())
                    .build(), HttpStatus.NOT_FOUND
        );
    }
}
