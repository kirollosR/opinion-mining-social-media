package com.example.opinionminingsocialmedia.services;

import com.example.opinionminingsocialmedia.controllers.AuthController;
import com.example.opinionminingsocialmedia.core.security.Response;
import com.example.opinionminingsocialmedia.core.security.TokenUtil;
import com.example.opinionminingsocialmedia.models.FileInfo;
import com.example.opinionminingsocialmedia.models.Post;
import com.example.opinionminingsocialmedia.models.Topic;
import com.example.opinionminingsocialmedia.models.User;
import com.example.opinionminingsocialmedia.payload.requests.PostRequest;
import com.example.opinionminingsocialmedia.repository.PostsRepository;
import com.example.opinionminingsocialmedia.services.file_storage_service.FilesStorageService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PostsServices {
    @Autowired
    private PostsRepository postsRepository;
    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private UserServices userServices;

    @Autowired
    private FilesStorageService filesStorageService;

    @Autowired
    private TopicService topicService;
    Logger log = LoggerFactory.getLogger(AuthController.class);


    public Page<Post> getAllPosts(int page, int pageSize) {
        final Pageable pageable = PageRequest.of(page, pageSize);
        return postsRepository.findAll(pageable);
    }

    public ResponseEntity<Page<Post>> getPostsByTopicName(String topic,int page, int pageSize) {
        final Pageable pageable = PageRequest.of(page, pageSize);
        return ResponseEntity.ok(postsRepository.findByTopicName(topic,pageable));
    }

    public ResponseEntity<Response> getPostById(int id) {
        Optional<Post> post = postsRepository.findById(id);
        if(post.isEmpty()) {
            return ResponseEntity.status(404).body(Response
                    .builder()
                    .success(false)
                    .message("Sorry The post you entered is not found")
                    .build());
        }
        return  ResponseEntity.ok().body(Response
                .builder()
                .success(true)
                .data(post)
                .message("Success")
                .build());
    }

//    public List<Post> getPostByTopicName(String topicName) {
//        return postsRepository.findByTopicName(topicName);
//    }

    public ResponseEntity<Response> addPost(PostRequest postRequest, HttpServletRequest httpServletRequest) {
        final String authHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        String token = authHeader.substring("Bearer ".length());
        String username = tokenUtil.getUserNameFromToken(token);
        Optional<User> user =  userServices.findByUserName(username);
        Optional<Topic> topic =  topicService.getTopicById(postRequest.getTopicId());
        if(user.isEmpty()) {
            return ResponseEntity.status(401).body(Response
                    .builder()
                    .success(false)
                    .message("Sorry you not authorized")
                    .build());
        }
        if(topic.isEmpty()) {
            return ResponseEntity.badRequest().body(Response
                    .builder()
                    .success(false)
                    .message("Sorry the topic id you entered is not found")
                    .build());
        }
        FileInfo fileInfo;
        try{
            Resource resource = filesStorageService.save(postRequest.getImage());
            fileInfo = FileInfo.builder()
                    .name(resource.getFilename())
                    .url(resource.getURI().toString())
                    .build();
            log.info(fileInfo.getUrl());
            log.info(fileInfo.getName());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Response
                    .builder()
                    .success(false)
                    .message(e.getMessage())
                    .build());
        }
        Post post = postsRepository.save(Post.builder()
                .content(postRequest.getContent())
                .image(fileInfo.getUrl())
                .topic(topic.get())
                .user(user.get())
                .postLikes(0)
                .postScore(Float.valueOf(0))
                .build());
        return ResponseEntity.ok(Response.builder()
                .success(true)
                .message("The Post saved successfully")
                .data(post)
                .build());
    }
}
