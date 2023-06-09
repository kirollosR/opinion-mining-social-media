package com.example.opinionminingsocialmedia.payload.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopicResponse {
    private Integer id;
    private String name;
    private UserResponse user;

}
