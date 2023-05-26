package com.app.petz.core.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetPostRequestJson {
    @NotNull
    private String name;
    @NotNull
    private Integer age;
    private String birthday;
    private Double weight;
    private String color;
    private String mainImageUrl;
}

