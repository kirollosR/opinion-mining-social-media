package com.example.opinionminingsocialmedia.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomException extends RuntimeException{
    @ResponseBody
    public Map<String, String> getErrorDetails() {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("message", getMessage());
        return errorDetails;
    }

    public CustomException(String message) {
        super(message);
    }
}
