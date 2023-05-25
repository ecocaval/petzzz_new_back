package com.app.petz.core.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePetResponseJson {
    private LocalDateTime timeStamp;
    private String message;
    private UUID petUuid;
}
