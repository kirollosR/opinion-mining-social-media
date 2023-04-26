package com.example.opinionminingsocialmedia.models;

import jakarta.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "keywords")
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    @NotEmpty(message = "Please enter a keyword")
    private String name;
    @Column(name = "score")
    @NotEmpty(message = "Please enter a score")
    @Max(value = 10, message = "Score must be less than 10")
    private Integer score;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Keyword() {
        super();
    }
    public Keyword(Integer keywordId, String keywordName, Integer keywordScore, User user) {
        this.id = keywordId;
        this.name = keywordName;
        this.score = keywordScore;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer keywordId) {
        this.id = keywordId;
    }

    public String getName() {
        return name;
    }

    public void setName(String keywordName) {
        this.name = keywordName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer keywordScore) {
        this.score = keywordScore;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
