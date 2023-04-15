package com.example.opinionminingsocialmedia.models;

import jakarta.persistence.*;

import java.sql.Date;


@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer postId;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    private Topic topic;
    @Column(name = "post_score")
    private Float postScore;
    @Column(name = "post_date")
    private Date postDate;
    @Column(name = "post_data")
    private String postData;
    @Column(name = "post_likes")
    private Integer postLikes;
}
