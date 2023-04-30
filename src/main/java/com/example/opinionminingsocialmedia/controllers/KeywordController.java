package com.example.opinionminingsocialmedia.controllers;

import com.example.opinionminingsocialmedia.core.security.Response;
import com.example.opinionminingsocialmedia.models.Keyword;
import com.example.opinionminingsocialmedia.payload.requests.KeywordRequest;
import com.example.opinionminingsocialmedia.services.KeywordService;
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
    public ResponseEntity<Response> getAllKeyword (){
        Response response = keywordService.getAllKeywords();
        if(response.isSuccess()){
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/keyword")
    public ResponseEntity<Response> getKeywordById (@RequestParam int id){
        Response response = keywordService.getKeywordById(id);
        if(response.isSuccess()){
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addKeyword")
    public ResponseEntity<Response> addKeyword(@Valid @RequestBody KeywordRequest keywordRequest){
        final Response response = keywordService.addKeyword(keywordRequest);
        if(response.isSuccess()){
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteKeyword")
    public ResponseEntity<Response> deleteKeyword(@RequestParam int id){
        Response response = keywordService.deleteKeyword(id);
        if(response.isSuccess()){
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateKeyword")
    public ResponseEntity<Response> updateKeyword(@Valid @RequestBody KeywordRequest keywordRequest, @RequestParam int id){
        Response response = keywordService.updateKeyword(keywordRequest, id);
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
