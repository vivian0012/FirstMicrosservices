package com.marcasMedicamentos.brands.controllers.exeptions;

import com.marcasMedicamentos.brands.service.exeption.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

// Mudança na mensagem de erro 505 no Postman
/*
* @ControllerAdvice é uma anotação no Spring que permite definir um manipulador global
* para exceções lançadas por qualquer controlador (controller) na sua aplicação.*/
@ControllerAdvice
public class ResourceExceptionHandler {

    /*
    * @ExceptionHandler é uma anotação que define um método que lida com uma exceção específica.
    * Quando uma exceção do tipo especificado é lançada em qualquer controlador,
    * o método anotado com @ExceptionHandler será invocado.*/
    @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<StandardErro> ResourceNotFount(ResourceNotFoundException a, HttpServletRequest request) {

            String error = "Obj não encontrado.";
            HttpStatus status = HttpStatus.NOT_FOUND; // 404
            StandardErro err = new StandardErro(
                    Instant.now(),            // timestamp
                    status.value(),           // Status do erro
                    error,                    // Mensagem de erro
                    a.getMessage(),           // Erro de exceção na classe ResourceNotFoundException
                    request.getRequestURI()); // Caminho do erro

            return ResponseEntity.status(status).body(err);
        }
}

