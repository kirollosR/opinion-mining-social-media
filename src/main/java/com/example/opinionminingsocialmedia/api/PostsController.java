package com.example.opinionminingsocialmedia.api;


import com.example.opinionminingsocialmedia.models.Post;
import com.example.opinionminingsocialmedia.services.PostsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PostsController {
    @Autowired
    private  PostsServices postsServices;
    @GetMapping("/posts")
    public ResponseEntity<Page<Post>> getAllPosts(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int pageSize) {
        final Page<Post> postsList = postsServices.getAllPosts(page, pageSize);
        return new ResponseEntity<Page<Post>>(postsList, HttpStatus.OK);
    }

    @GetMapping("/posts/{id}")
    public Post  getPostById(@PathVariable int id) {
        return postsServices.getPostById(id);
    }

//    @GetMapping("/postsImage")
//    public List<String> getPostImage() {
//        final Page<Post> postsList = postsServices.getAllPosts(0, 10);
////        final List<String> posts = postsList.get().map(e -> e.getImage().getOriginalFilename()).toList();
//        return ArrayList<>;
//    }

//    @GetMapping("/postsByTopic")
//    public List<Post> getPostByTopic(@RequestParam String topicName) {
//        final List<Post> postsList = postsServices.getPostByTopicName(topicName);
//        return postsList;
//    }

    @PostMapping("/post")
    public Post addPost(@ModelAttribute Post postContent) {
        final Post post = postsServices.addPost(postContent);
        return post;
    }
}
