package com.example.opinionminingsocialmedia.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gender_id")
    private Gender gender;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;
    @Column(name = "profile_url")
    private String profileUrl;
    @Column(name = "status")
    private String status;

    public User() {
        super();
    }
    public User(Integer userId, String userFirstname, String userLastname, String userEmail, String username, String password, Gender gender, Role role, String userProfile, String userStatus) {
        this.id = userId;
        this.firstname = userFirstname;
        this.lastname = userLastname;
        this.email = userEmail;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.role = role;
        this.profileUrl = userProfile;
        this.status = userStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String userFirstname) {
        this.firstname = userFirstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String userLastname) {
        this.lastname = userLastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String userEmail) {
        this.email = userEmail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String userProfile) {
        this.profileUrl = userProfile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String userStatus) {
        this.status = userStatus;
    }
}
