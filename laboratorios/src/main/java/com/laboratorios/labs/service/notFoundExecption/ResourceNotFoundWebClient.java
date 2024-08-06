package com.laboratorios.labs.service.notFoundExecption;

import java.io.Serial;

public class ResourceNotFoundWebClient extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundWebClient(Object id) {

        super("IDs mencionado na listagem não corresponde ao cadastrado no Banco de Dados. ID: " + id);
    }
}
