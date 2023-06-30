package com.app.petz.exception;

import com.app.petz.core.dto.CpfSIzeDefailsDto;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class InvalidCpfExceptionDetails {
    protected String title;
    protected Integer status;
    protected CpfSIzeDefailsDto details;
    protected LocalDateTime timestamp;
}
