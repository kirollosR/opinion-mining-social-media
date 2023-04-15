package com.example.opinionminingsocialmedia.models;

import jakarta.persistence.*;

@Entity
@Table(name = "gender")
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gender_id")
    private Integer genderId;
    @Column(name = "gender_name")
    private String genderName;

}
