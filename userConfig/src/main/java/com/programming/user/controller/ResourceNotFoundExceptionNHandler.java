package com.programming.user.controller;

import com.programming.user.service.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jdk.jshell.Snippet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;


@ControllerAdvice
public class ResourceNotFoundExceptionNHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> standardErrorResponseEntity(
            ResourceNotFoundException e, HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String error = "Obj não encontrado.";
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(err);
    }
}
