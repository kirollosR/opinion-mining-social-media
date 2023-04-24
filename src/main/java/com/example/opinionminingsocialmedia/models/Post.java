package com.example.opinionminingsocialmedia.models;

import jakarta.persistence.*;

import java.sql.Date;


@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "topic_id")
    private Topic topic;
    @Column(name = "score")
    private Float score;
    @Column(name = "date")
    private Date date;
    @Column(name = "data")
    private String data;
    @Column(name = "likes")
    private Integer likes;

    public Post() {
        super();
    }

    public Post(Integer postId, User user, Topic topic, Float postScore, Date postDate, String postData, Integer postLikes) {
        this.id = postId;
        this.user = user;
        this.topic = topic;
        this.score = postScore;
        this.date = postDate;
        this.data = postData;
        this.likes = postLikes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer postId) {
        this.id = postId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float postScore) {
        this.score = postScore;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date postDate) {
        this.date = postDate;
    }

    public String getData() {
        return data;
    }

    public void setData(String postData) {
        this.data = postData;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer postLikes) {
        this.likes = postLikes;
    }
}
