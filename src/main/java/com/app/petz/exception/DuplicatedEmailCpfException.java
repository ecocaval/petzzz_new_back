package com.app.petz.exception;

public class DuplicatedEmailCpfException extends RuntimeException{
    public DuplicatedEmailCpfException(String message, String email, String cpf){
        super(message);
        this.email = email;
        this.cpf = cpf;
    }

    private final String email;
    private final String cpf;

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }
}
