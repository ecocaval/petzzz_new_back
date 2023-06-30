package com.app.petz.core.utils;

import com.app.petz.core.dto.CpfSIzeDefailsDto;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class CpfValidator {

    private final Integer CPF_DEFAULT_SIZE = 11;
    private final String CPF_REGEX = "^(\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2})$";

    public Boolean validateCpf(String cpf) {
        return Pattern.matches(CPF_REGEX, cpf);
    }

    public String cleanCpfCharacter(String cpf) {
        return cpf.replace(".", "")
                  .replace("-", "");
    }

    public CpfSIzeDefailsDto calculateSizeDifference(String cpf) {
        if(cpf.isBlank() || cpf.isEmpty()) {
            return null;
        }
        return CpfSIzeDefailsDto.builder()
                                .sizeDifference(String.valueOf(Math.abs(CPF_DEFAULT_SIZE - cpf.length())))
                                .cpfSize(String.valueOf(cpf.length()))
                                .defaultCpfSize(String.valueOf(CPF_DEFAULT_SIZE))
                                .build();
    }
}
