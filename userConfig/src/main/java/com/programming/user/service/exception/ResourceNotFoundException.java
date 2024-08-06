package com.programming.user.service.exception;

import org.springframework.context.annotation.Configuration;

import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException{

    @Serial
    private static final long serialversionUID = 1L;

    public ResourceNotFoundException(Object id){
        super("Id não encontrado no banco de dados: " + id);
    }
}
