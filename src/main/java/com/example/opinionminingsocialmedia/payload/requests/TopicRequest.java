package com.example.opinionminingsocialmedia.payload.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopicRequest {
    @NotBlank(message = "Please enter a keyword")
    private String name;
    @NotNull(message = "Please enter a user id")
    @Min(1)
    private Integer userID;

}
