package com.example.opinionminingsocialmedia.repositories;

import com.example.opinionminingsocialmedia.models.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Integer> {
//    List<Keyword> findByUserId (Integer userId);
    @Query("select keyword from Keyword keyword join keyword.user user where user.id = :userId")
    List<Keyword> findByUserId (Integer userId);
}
