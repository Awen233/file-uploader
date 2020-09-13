package com.example.fileloader.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity handleException(FileNotFoundException exc){
        return new ResponseEntity(exc.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity handleException(Exception exc){
        return new ResponseEntity(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
