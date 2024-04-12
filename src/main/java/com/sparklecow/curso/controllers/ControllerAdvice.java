package com.sparklecow.curso.controllers;

import com.sparklecow.curso.exceptions.ErrorMessage;
import jakarta.persistence.NoResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = NoResultException.class)
    public ResponseEntity<ErrorMessage> noResultExceptionHandler(NoResultException noResultException){
        ErrorMessage errorMessage = new ErrorMessage("Error 404 Not found ", noResultException.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
