package com.example.opinionminingsocialmedia.payload.requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KeywordRequest {
    private Integer id;
    @NotBlank(message = "Please enter a keyword")
    private String name;
    @NotNull(message = "Please enter a score")
    @Min(value = 2, message = "Score must be greater than 2")
    @Max(value = 10, message = "Score must be less than 10")
    private Integer score;


    @AssertTrue(message = "Score must be an even number")
    private boolean isEven() {
        if (score == null) {
            return true;
        }
        return score % 2 == 0;
    }
}
