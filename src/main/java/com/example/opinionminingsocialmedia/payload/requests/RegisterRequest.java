package com.example.opinionminingsocialmedia.payload.requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty()
    @Size(min = 3, max = 20)
    private String username;
    @NotEmpty
    @Size(max = 50)
    @Email
    private String email;
    @NotEmpty
    @Size(min = 6, max = 40)
    private String password;

    @NotNull
    private Integer gender;
}
