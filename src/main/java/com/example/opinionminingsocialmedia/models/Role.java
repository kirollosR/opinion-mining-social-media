package com.example.opinionminingsocialmedia.models;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;

public Role() {
        super();
    }
    public Role(Integer roleId, String roleName) {
        this.id = roleId;
        this.name = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer roleId) {
        this.id = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String roleName) {
        this.name = roleName;
    }
}
