package com.example.opinionminingsocialmedia.payload.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopicRequest {
    private Integer id;
    private String name;
    private Integer userID;

}
