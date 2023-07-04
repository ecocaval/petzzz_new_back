package com.app.petz.exception;

import com.app.petz.core.dto.DuplicatedEmailCpfDto;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class DuplicatedEmailCpfExceptionDetails {
    protected String title;
    protected Integer status;
    protected DuplicatedEmailCpfDto details;
    protected LocalDateTime timestamp;
}
