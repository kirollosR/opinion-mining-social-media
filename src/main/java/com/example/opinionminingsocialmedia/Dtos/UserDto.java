package com.example.opinionminingsocialmedia.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
//    private Integer id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String role;

}
