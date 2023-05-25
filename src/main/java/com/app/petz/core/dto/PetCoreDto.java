package com.app.petz.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetCoreDto {
    private UUID id;
    private LocalDateTime creationDate;
    private String name;
    private Integer age;
    private LocalDate birthday;
    private Double weight;
    private String color;
}
