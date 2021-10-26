package com.example.productservice.command.product;

import com.example.productservice.model.error.ErrorMassageModel;
import org.axonframework.commandhandling.CommandExecutionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ProductErrorHandler {
    @ExceptionHandler(value = {IllegalStateException.class})
    public ResponseEntity<Object> handleIllegalStateException(IllegalStateException ex, WebRequest request) {

        ErrorMassageModel errorMassageModel = new ErrorMassageModel(new Date(), ex.getLocalizedMessage());

        return new ResponseEntity<>(errorMassageModel, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleOtherException(Exception ex, WebRequest request) {
        ErrorMassageModel errorMassageModel = new ErrorMassageModel(new Date(), ex.getLocalizedMessage());
        return new ResponseEntity<>(errorMassageModel, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {CommandExecutionException.class})
    public ResponseEntity<Object> handleCommandExecutionException(CommandExecutionException ex, WebRequest request) {
        ErrorMassageModel errorMassageModel = new ErrorMassageModel(new Date(), ex.getLocalizedMessage());
        return new ResponseEntity<>(errorMassageModel, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
