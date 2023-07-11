package com.app.petz.model;

import com.app.petz.core.requests.PetPostRequestJson;
import com.app.petz.core.requests.PetPutRequestJson;
import com.app.petz.core.responses.PetGetResponseJson;
import com.app.petz.core.responses.PetPostResponseJson;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private Boolean removed = false;

    @Column(nullable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column()
    private LocalDate birthday;

    @Column()
    private Double weight;

    @Column(length = 50)
    private String color;

    @Column()
    private String mainImageUrl;

    public static Pet fromCreateRequest(PetPostRequestJson createRequest, DateTimeFormatter birthdayFormatter) {
        return Pet.builder()
                  .name(createRequest.name())
                  .age(createRequest.age())
                  .creationDate(LocalDateTime.now())
                  .removed(false)
                  .birthday(LocalDate.parse(createRequest.birthday(), birthdayFormatter))
                  .weight(createRequest.weight())
                  .color(createRequest.color())
                  .mainImageUrl(createRequest.mainImageUrl())
                  .build();
    }

    public static Pet fromPutRequest(Pet pet, PetPutRequestJson petPutRequestJson,  DateTimeFormatter birthdayFormatter){
        return Pet.builder()
                  .id(pet.getId())
                  .removed(pet.getRemoved())
                  .creationDate(pet.getCreationDate())
                  .name(petPutRequestJson.name() != null ? petPutRequestJson.name() : pet.getName())
                  .age(petPutRequestJson.age() != null ? petPutRequestJson.age() : pet.getAge())
                  .birthday(petPutRequestJson.birthday() != null ? LocalDate.parse(petPutRequestJson.birthday(), birthdayFormatter) : pet.getBirthday())
                  .weight(petPutRequestJson.weight() != null ? petPutRequestJson.weight() : pet.getWeight())
                  .color(petPutRequestJson.color() != null ? petPutRequestJson.color() : pet.getColor())
                  .mainImageUrl(petPutRequestJson.mainImageUrl() != null ? petPutRequestJson.mainImageUrl() : pet.getMainImageUrl())
                  .build();
    }

    public static PetGetResponseJson toGetResponseJson(Pet pet){
        return PetGetResponseJson.builder()
                                 .id(pet.getId())
                                 .name(pet.getName())
                                 .age(pet.getAge())
                                 .birthday(pet.getBirthday())
                                 .weight(pet.getWeight())
                                 .color(pet.getColor())
                                 .mainImageUrl(pet.getMainImageUrl())
                                 .build();
    }

    public static PetPostResponseJson toPostResponseJson(Pet pet) {
        return PetPostResponseJson.builder()
                                  .pet(pet)
                                  .message("O pet " + pet.getName() + " foi criado com sucesso.")
                                  .timeStamp(LocalDateTime.now())
                                  .build();
    }
}