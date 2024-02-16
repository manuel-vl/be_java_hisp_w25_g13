package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> badRequest(BadRequestException e){
        BadRequestException badRequestException = new BadRequestException(e.getMessage());
        return new ResponseEntity<>(badRequestException, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notfound(NotFoundException e){
        NotFoundException notFoundException = new NotFoundException(e.getMessage());
        return new ResponseEntity<>(notFoundException, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(WrongDataException.class)
    public ResponseEntity<?> wrondData(WrongDataException e){
        WrongDataException wrongDataException = new WrongDataException(e.getMessage());
        return new ResponseEntity<>(wrongDataException,HttpStatus.BAD_REQUEST);
    }
}
