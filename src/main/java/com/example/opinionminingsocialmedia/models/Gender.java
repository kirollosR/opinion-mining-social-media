package com.example.opinionminingsocialmedia.models;

import jakarta.persistence.*;

@Entity
@Table(name = "gender")
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;

    public Gender() {
        super();
    }
    public Gender(Integer genderId, String genderName) {
        this.id = genderId;
        this.name = genderName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer genderId) {
        this.id = genderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String genderName) {
        this.name = genderName;
    }
}
