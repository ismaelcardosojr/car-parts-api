package br.com.mildevs.carParts.exceptionHandling;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.mildevs.carParts.exception.PartNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(PartNotFoundException.class)
    public ResponseEntity<Object> handlePartNotFound(PartNotFoundException exception) {
        ExceptionResponse response = new ExceptionResponse();

        response.setMessage("Part not found");
        response.setDateTime(LocalDateTime.now());
        
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleInternalError(Exception exception) {
        ExceptionResponse response = new ExceptionResponse();

        response.setMessage("An internal server error occurred");
        response.setDateTime(LocalDateTime.now());
        
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}