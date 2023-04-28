package com.example.opinionminingsocialmedia.Dtos;

import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true)
public class UserRequest {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRequest(String userName, String password) {
        this.username = userName;
        this.password = password;
    }
}
