package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, List<String>> body = new HashMap<>();

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, BAD_REQUEST);
    }

    //@ExceptionHandler(HttpMessageNotReadableException.class)
    //public ResponseEntity handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        final Throwable cause = ex.getCause();
        if (cause instanceof InvalidFormatException invalidFormatException) {
            if (invalidFormatException.getMessage().startsWith("Cannot deserialize value of type `javax.xml.datatype.XMLGregorianCalendar`")) {
                String fieldName = invalidFormatException.getPath().get(0).getFieldName();
                return new ResponseEntity<>(fieldName + " should be in yyyy-MM-dd format", BAD_REQUEST);
            }
        } else if (cause instanceof JsonMappingException jsonMappingException) {
            return new ResponseEntity<>(jsonMappingException.getMessage(), BAD_REQUEST);
        } else if (cause instanceof JsonProcessingException jsonProcessingException) {
            return new ResponseEntity<>(jsonProcessingException.getLocalizedMessage(), BAD_REQUEST);
        }
        System.out.println("test");
        return new ResponseEntity<>("bad request", BAD_REQUEST);
    }

}
