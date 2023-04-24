package com.example.opinionminingsocialmedia.models;

import jakarta.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "score")
    private Integer score;
    @Column(name = "date")
    private Date date;
    @Column(name = "data")
    private String data;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private Post post;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    public Comment() {
        super();
    }
    public Comment(Integer commentId, Integer commentScore, Date commentDate, String commentData, Post post, User user, Topic topic) {
        this.id = commentId;
        this.score = commentScore;
        this.date = commentDate;
        this.data = commentData;
        this.post = post;
        this.user = user;
        this.topic = topic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer commentId) {
        this.id = commentId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer commentScore) {
        this.score = commentScore;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date commentDate) {
        this.date = commentDate;
    }

    public String getData() {
        return data;
    }

    public void setData(String commentData) {
        this.data = commentData;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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
}
