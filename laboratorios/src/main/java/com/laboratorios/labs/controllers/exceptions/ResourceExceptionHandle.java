package com.laboratorios.labs.controllers.exceptions;

import com.laboratorios.labs.service.notFoundExecption.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandle {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardErro> standardErroResponseEntity(
            ResourceNotFoundException a,
            HttpServletRequest request) {

        String erro = "Erro ao tentar localizar o ID";
        HttpStatus status = HttpStatus.NOT_FOUND;

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
