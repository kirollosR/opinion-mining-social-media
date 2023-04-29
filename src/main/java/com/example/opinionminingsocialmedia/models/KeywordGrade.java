package com.example.opinionminingsocialmedia.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "keyword_grades")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class KeywordGrade {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "grade")
    private String grade;

}
