package com.empresa.company.controller;

import com.empresa.company.service.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceNotFoundExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> response (
            ResourceNotFoundException e, HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String erro = "Obj não encontrado.";
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                erro,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(err);
    }
}
