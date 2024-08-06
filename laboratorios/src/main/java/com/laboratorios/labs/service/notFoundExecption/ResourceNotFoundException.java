package com.laboratorios.labs.service.notFoundExecption;


import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(Object id){
        super("ID informado não encontrado em base de dados: " + id);
    }

}
