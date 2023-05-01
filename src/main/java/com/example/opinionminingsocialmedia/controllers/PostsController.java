package com.example.opinionminingsocialmedia.controllers;


import com.example.opinionminingsocialmedia.core.security.Response;
import com.example.opinionminingsocialmedia.models.Post;
import com.example.opinionminingsocialmedia.payload.requests.PostRequest;
import com.example.opinionminingsocialmedia.services.PostsServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PostsController {
    @Autowired
    private PostsServices postsServices;

    @GetMapping("/posts")
    public ResponseEntity<Page<Post>> getAllPosts(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int pageSize) {
        final Page<Post> postsList = postsServices.getAllPosts(page, pageSize);
        return new ResponseEntity<Page<Post>>(postsList, HttpStatus.OK);
    }

//    @GetMapping("/posts/{id}")
//    public Post getPostById(@PathVariable int id) {
//        return postsServices.getPostById(id);
//    }

//    @GetMapping("/postsImage")
//    public List<String> getPostImage() {
//        final Page<Post> postsList = postsServices.getAllPosts(0, 10);
////        final List<String> posts = postsList.get().map(e -> e.getImage().getOriginalFilename()).toList();
//        return ArrayList<>;
//    }

    @GetMapping("/postsByTopic/")
    public ResponseEntity<Page<Post>> getPostByTopic(@RequestParam String topicName,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int pageSize) {
        return postsServices.getPostsByTopicName(topicName, page, pageSize);
    }

    @PostMapping(value = "/post", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Response> addPost(@RequestParam() String content,
                                            @RequestParam() MultipartFile image,
                                            @RequestParam() Integer topicId,
                                            HttpServletRequest request,
                                            HttpServletResponse response) {
        return postsServices.addPost(PostRequest.builder()
                .content(content)
                .image(image)
                .topicId(topicId)
                .build(), request);
    }

//    @PutMapping(value = "/post/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<Response> editPost(@PathParam (value = id)@RequestParam() String content,
//                                            @RequestParam() MultipartFile image,
//                                            @RequestParam() Integer topicId,
//                                            HttpServletRequest request,
//                                            HttpServletResponse response) {
//        return postsServices.addPost(PostRequest.builder()
//                .content(content)
//                .image(image)
//                .topicId(topicId)
//                .build(), request);
//    }
}
