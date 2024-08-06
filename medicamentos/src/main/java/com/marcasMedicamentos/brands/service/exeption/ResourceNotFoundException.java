package com.marcasMedicamentos.brands.service.exeption;


import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException{

    @Serial
    private static final long serialversionUID = 1L;

    public ResourceNotFoundException(Object id){
        super("Verifique o ID e tente novamente: " + id);
    }
}
