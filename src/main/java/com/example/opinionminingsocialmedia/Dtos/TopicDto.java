package com.example.opinionminingsocialmedia.Dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TopicDto {
    private Integer id;
    private String name;
    private UserDto user;

    public TopicDto(Integer id, String name, UserDto user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }
}
