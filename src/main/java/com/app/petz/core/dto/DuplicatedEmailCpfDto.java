package com.app.petz.core.dto;

import lombok.Builder;

@Builder
public record DuplicatedEmailCpfDto(String email, String cpf) {
}
