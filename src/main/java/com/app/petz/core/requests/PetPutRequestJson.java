package com.app.petz.core.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
