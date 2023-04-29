package com.example.opinionminingsocialmedia.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TopicDto {
    private Integer id;
    private String name;
    private Integer userID;

}
