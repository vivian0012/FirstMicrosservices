package com.empresa.company.service.exception;

import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException (Object id){
        super("Id não localizado no banco de dados: " + id);
    }

}
