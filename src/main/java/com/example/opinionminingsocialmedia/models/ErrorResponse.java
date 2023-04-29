package com.example.opinionminingsocialmedia.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@Data
@Builder
public class ErrorResponse {
    //General error message about nature of error
    private String message;
    private String statusCode;
    //Specific errors in API request processing
    private List<String> details;
}
