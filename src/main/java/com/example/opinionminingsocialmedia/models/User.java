package com.example.opinionminingsocialmedia.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "user_firstname")
    private String userFirstname;
    @Column(name = "user_lastname")
    private String userLastname;
    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @ManyToOne(cascade = CascadeType.ALL)
    private Gender gender;
    @ManyToOne(cascade = CascadeType.ALL)
    private Role role;
    @Column(name = "user_profile")
    private String userProfile;
    @Column(name = "user_status")
    private String userStatus;
}
