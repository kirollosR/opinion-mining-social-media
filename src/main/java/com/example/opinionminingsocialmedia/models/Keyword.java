package com.example.opinionminingsocialmedia.models;

import jakarta.persistence.*;

@Entity
@Table(name = "keywords")
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_id")
    private Integer keywordId;
    @Column(name = "keyword_name")
    private String keywordName;
    @Column(name = "keyword_score")
    private Integer keywordScore;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

}
