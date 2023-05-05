package com.example.opinionminingsocialmedia.controllers;

import com.example.opinionminingsocialmedia.core.security.Response;
import com.example.opinionminingsocialmedia.models.Keyword;
import com.example.opinionminingsocialmedia.payload.requests.KeywordRequest;
import com.example.opinionminingsocialmedia.services.KeywordService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import com.example.opinionminingsocialmedia.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class KeywordController {
    @Autowired
    KeywordService keywordService;
    @Autowired
    UserServices userServices;

    @GetMapping("/keywords")
    public ResponseEntity<Response> getAllKeyword (HttpServletRequest request){
        Response response = keywordService.getAllKeywords(request);
        if(response.isSuccess()){
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/keyword")
    public ResponseEntity<Response> getKeywordById (@RequestParam int id, HttpServletRequest request){
        Response response = keywordService.getKeywordById(id, request);
        if(response.isSuccess()){
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addKeyword")
    public ResponseEntity<Response> addKeyword(@Valid @RequestBody KeywordRequest keywordRequest, HttpServletRequest request){
        final Response response = keywordService.addKeyword(keywordRequest, request);
        if(response.isSuccess()){
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteKeyword")
    public ResponseEntity<Response> deleteKeyword(@RequestParam int id, HttpServletRequest request){
        Response response = keywordService.deleteKeyword(id, request);
        if(response.isSuccess()){
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateKeyword")
    public ResponseEntity<Response> updateKeyword(@Valid @RequestBody KeywordRequest keywordRequest, @RequestParam int id, HttpServletRequest request){
        Response response = keywordService.updateKeyword(keywordRequest, id, request);
        if(response.isSuccess()){
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/keywordByName")
    public ResponseEntity<Response> getKeywordByName (@RequestParam String name){
        Response response = keywordService.getKeywordByName(name);
        if(response.isSuccess()){
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
        }
    }

    // TO TEST GETTING BY FOREIGN KEY
    @GetMapping("/keywordsByUserId")
    public List<Object> findByUserId (@RequestParam Integer userId){
        return keywordService.findByUserId(userId);
    }
}
