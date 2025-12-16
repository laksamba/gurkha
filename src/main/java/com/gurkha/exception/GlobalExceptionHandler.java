package com.gurkha.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FileNotFound.class)
    public ResponseEntity<String> handleUserNotFound(FileNotFound ex){
        return new ResponseEntity<>(ex.getMessage()+ ex.getCause(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(UserNotFound.class)
    public  ResponseEntity<String> handleUserNotFound(UserNotFound obj){
        return  new ResponseEntity<>(obj.getMessage() + obj.getCause(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String,String>> handleBadRequest(BadRequestException obj){
        Map<String , String> error= new HashMap<>();
        error.put("error",obj.getMessage());
        return  new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public  ResponseEntity<Map<String,String>> handleResourceNotFoundException(ResourceNotFoundException obj){
         Map<String , String> error=new HashMap<>();
         error.put("error",obj.getMessage());
         return  new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
    }

