package com.hotelservice.exception;

import com.sun.javafx.collections.MappingChange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handleResourceNotFoundException(Exception ex){

        Map map = new HashMap<>();
        map.put("message",ex.getMessage());
        map.put("success",false);
        map.put("status",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);

    }
}
