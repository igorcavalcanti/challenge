package com.example.melichallenge.exception.handler;

import com.example.melichallenge.exception.InvalidRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<HashMap<String, String>> malformedParametersRequest(InvalidRequestException e, WebRequest request) {
        HashMap<String, String> response = new HashMap<>();
        response.put("message", e.getMessage());

        return ResponseEntity.status(BAD_REQUEST)
                .contentType(APPLICATION_JSON)
                .body(response);
    }
}
