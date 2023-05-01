package com.example.opinionminingsocialmedia.controllers;


import com.example.opinionminingsocialmedia.core.security.Response;
import com.example.opinionminingsocialmedia.models.Post;
import com.example.opinionminingsocialmedia.payload.requests.CommentRequest;
import com.example.opinionminingsocialmedia.payload.requests.PostRequest;
import com.example.opinionminingsocialmedia.services.CommentsServices;
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

@RestController
@RequestMapping("/api/v1")
public class CommentsController {
    @Autowired
    private CommentsServices commentsServices;

    @GetMapping("/comments/{postId}")
    public ResponseEntity<Response> getAllCommentsByPostId(@PathParam("postId") Integer postId,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int pageSize) {
        return commentsServices.getAllPostComments(postId, page, pageSize);
    }
 
//    @GetMapping("/posts/{id}")
//    public Post getPostById(@PathVariable int id) {
//        return postsServices.getPostById(id);
//    }

    @PostMapping("/comments")
    public ResponseEntity<Response> addComment(@Valid @RequestBody CommentRequest commentRequest,
                                               HttpServletRequest httpServletRequest,
                                               HttpServletResponse httpServletResponse) {
        return commentsServices.addComment(commentRequest, httpServletRequest);
    }

//    @GetMapping("/postsByTopic")
//    public List<Post> getPostByTopic(@RequestParam String topicName) {
//        final List<Post> postsList = postsServices.getPostByTopicName(topicName);
//        return postsList;
//    }

//    @PostMapping(value = "/post", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<Response> addPost(@RequestParam() String content,
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
