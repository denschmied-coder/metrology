
package com.denschmied.metrology.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {
 // для  ошибок валидации
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handlerNotReadable(
            HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body("Validation exception: " + e.getMessage());
    }
     //  Обработчик общих исключений
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handlerException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
    }       
}
