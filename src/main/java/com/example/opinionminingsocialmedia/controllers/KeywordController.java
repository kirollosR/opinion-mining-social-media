package com.example.opinionminingsocialmedia.controllers;

import com.example.opinionminingsocialmedia.models.Keyword;
import com.example.opinionminingsocialmedia.services.KeywordService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KeywordController {
    @Autowired
    KeywordService keywordService;

    @GetMapping("/keywords")
    public List<Keyword> getAllKeywords (){
        return keywordService.getAllKeywords();
    }

    @GetMapping("/keyword")
    public Keyword getKeywordById (@RequestParam int id){
        return keywordService.getKeywordById(id);
    }

//    @GetMapping("/keyword1")
//    public Map getKeywordById1 (@RequestParam int id){
//        Keyword keyword =keywordService.getKeywordById(id);
//        Map<String, Object> keywordMap = new HashMap<>();
//        if (keyword == null) {
//            keywordMap.put("status", "error");
//            keywordMap.put("message", "keyword not found");
//        } else {
//            keywordMap.put("status", "success");
//            keywordMap.put("message", "keyword found");
//            keywordMap.put("keyword", keyword);
//        }
//        return keywordMap;
//    }

    @PostMapping("/addKeyword")
    public ResponseEntity<Keyword> addKeyword(@Valid @RequestBody Keyword keyword){
        keywordService.addKeyword(keyword);
        return new ResponseEntity<Keyword>(keyword, HttpStatus.OK);
    }

    // TO TEST GETTING BY FOREIGN KEY
    @GetMapping("/keywordsByUserId")
    public List<Keyword> findByUserId (@RequestParam Integer userId){
        return keywordService.findByUserId(userId);
    }
}
