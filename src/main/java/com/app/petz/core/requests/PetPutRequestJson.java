package com.app.petz.core.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetPutRequestJson {
    private String name;
    private Integer age;
    private String birthday;
    private Double weight;
    private String color;
    private String mainImageUrl;
}
