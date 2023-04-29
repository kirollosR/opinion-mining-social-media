package com.example.opinionminingsocialmedia.payload.responses;

import com.example.opinionminingsocialmedia.models.KeywordGrade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KeywordResponse {
    private Integer id;
    private String name;
    private KeywordGrade score;
    private UserResponse user;
}
