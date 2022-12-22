package br.com.mildevs.carParts.exceptionHandling;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ExceptionResponse {

    private String message;
    private LocalDateTime dateTime;
    
}