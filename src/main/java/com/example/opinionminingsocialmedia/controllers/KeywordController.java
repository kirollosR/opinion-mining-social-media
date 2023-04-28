package com.example.opinionminingsocialmedia.controllers;

import com.example.opinionminingsocialmedia.core.security.Response;
import com.example.opinionminingsocialmedia.models.Keyword;
import com.example.opinionminingsocialmedia.services.KeywordService;
import javax.validation.Valid;

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
public class KeywordController {
    @Autowired
    KeywordService keywordService;
    @Autowired
    UserServices userServices;

    @GetMapping("/keywords")
    @RequestMapping("/api/v1")
    public MappingJacksonValue getAllKeywords (){

        Filter filter = new Filter();
        List<Keyword> keywords = keywordService.getAllKeywords();
        Set<String> hash_Set = new HashSet<String>();
        hash_Set.add("password");
        hash_Set.add("role");
        hash_Set.add("authorities");
        hash_Set.add("active");
        MappingJacksonValue mappingJacksonValue = filter.filter(keywords, hash_Set, "userFilter");

        return mappingJacksonValue;
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
    public ResponseEntity<Response> addKeyword(@Valid @RequestBody Keyword keyword){
        final Response response = keywordService.addKeyword(keyword);
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
