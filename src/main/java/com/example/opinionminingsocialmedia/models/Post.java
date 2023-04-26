package com.example.opinionminingsocialmedia.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "content", nullable = false)
    private String content;
    @Lob
    @Transient
    @Column(name = "image", nullable = false)
    private MultipartFile image;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "topic_id")
    private Topic topic;
    @Column(name = "post_score")
    private Float postScore;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, name = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Timestamp createdAt;
    @Column(name = "post_likes", columnDefinition = "integer default 0")
    private Integer postLikes;
}
