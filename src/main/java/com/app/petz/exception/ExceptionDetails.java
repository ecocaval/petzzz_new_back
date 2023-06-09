package com.app.petz.exception;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExceptionDetails {
    protected String title;
    protected Integer status;
    protected String details;
    protected String developerMessage;
    protected LocalDateTime timestamp;
}
