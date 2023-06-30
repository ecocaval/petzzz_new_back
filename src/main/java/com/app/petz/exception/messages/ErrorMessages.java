package com.app.petz.exception.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessages {
    EMAIL_IS_ALREADY_IN_USE("Este email já está sendo utilizado por outro usuário."),
    CPF_IS_ALREADY_IN_USE("Este cpf já está sendo utilizado por outro usuário."),
    EMAIL_AND_CPF_ARE_ALREADY_IN_USE("Este email e cpf já estão sendo utilizados por outro usuário."),
    INVALID_CPF("Este cpf não é valido, verifique o formato e o tamanho.");

    private final String errorMessage;
}
