package com.laboratorios.labs.controllers.exceptions;

import com.laboratorios.labs.service.notFoundExecption.ResourceNotFoundWebClient;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandlerWebClient {

    @ExceptionHandler(ResourceNotFoundWebClient.class)

    public ResponseEntity<StandardErro> exceptionHandlerWebClientResponseEntity(
            ResourceNotFoundWebClient a,
            HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String erro = "Problemas ao localizar um dos IDs na ListagemParaMedicamentos.";

        StandardErro standardErro = new StandardErro(
                Instant.now(),
                status.value(),
                erro,
                a.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(standardErro);
    }
}
