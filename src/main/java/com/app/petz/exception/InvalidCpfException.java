package com.app.petz.exception;

public class InvalidCpfException extends RuntimeException{
    public InvalidCpfException(String message, String cpf){
        super(message);
        this.cpf = cpf;
    }

    private final String cpf;

    public String getCpf() {
        return cpf;
    }
}
