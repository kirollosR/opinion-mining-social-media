package com.example.opinionminingsocialmedia.controllers;

import com.example.opinionminingsocialmedia.core.security.Response;
import com.example.opinionminingsocialmedia.payload.requests.TopicRequest;
import com.example.opinionminingsocialmedia.services.TopicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @PostMapping("/addTopic")
    public ResponseEntity<Response> addTopic(@Valid @RequestBody TopicRequest topic){
        Response response = topicService.addTopic(topic);
        if(response.isSuccess()){
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/topics")
    public ResponseEntity<Response> getAllTopics (){
        Response response = topicService.getAllTopics();
        if(response.isSuccess()){
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteTopic")
    public ResponseEntity<Response> deleteTopic(@RequestParam Integer id){
        Response response = topicService.deleteTopic(id);
        if(response.isSuccess()){
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
