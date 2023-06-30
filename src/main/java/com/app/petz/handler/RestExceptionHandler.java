package com.app.petz.handler;

import com.app.petz.core.dto.DuplicatedEmailCpfDto;
import com.app.petz.core.utils.CpfValidator;
import com.app.petz.exception.*;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Log4j2
@AllArgsConstructor
public class RestExceptionHandler {

    private final CpfValidator cpfValidator;

    @ExceptionHandler(PetNotFoundException.class)
    protected ResponseEntity<PetNotFoundExceptionDetails> handlePetNotFoundException(PetNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            PetNotFoundExceptionDetails.builder()
                                       .timestamp(LocalDateTime.now())
                                       .status(HttpStatus.NOT_FOUND.value())
                                       .title(exception.getCause().getMessage())
                                       .details(exception.getMessage())
                                       .developerMessage(exception.getClass().getName())
                                       .build());
    }

    @ExceptionHandler(DuplicatedEmailCpfException.class)
    protected ResponseEntity<DuplicatedEmailCpfExceptionDetails> handleDuplicatedEmailOrCpfException(
            DuplicatedEmailCpfException exception
    ){

        DuplicatedEmailCpfDto duplicatedEmailCpfDto = DuplicatedEmailCpfDto.builder()
                                                                .email(exception.getEmail())
                                                                .cpf(exception.getCpf())
                                                                .build();

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                DuplicatedEmailCpfExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.FORBIDDEN.value())
                        .title(exception.getMessage())
                        .details(duplicatedEmailCpfDto)
                        .build());
    }

    @ExceptionHandler(InvalidCpfException.class)
    protected ResponseEntity<InvalidCpfExceptionDetails> handleInvalidCpfException(
            InvalidCpfException exception
    ){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                InvalidCpfExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.FORBIDDEN.value())
                        .title(exception.getMessage())
                        .details(cpfValidator.calculateSizeDifference(exception.getCpf()))
                        .build());
    }
}
