package com.example.opinionminingsocialmedia.repository;

import com.example.opinionminingsocialmedia.models.Keyword;
import com.example.opinionminingsocialmedia.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Integer> {
//    List<Keyword> findByUserId (Integer userId);
    @Query("SELECT k.name, k.score, u.username FROM Keyword k JOIN k.user u WHERE u.id = :userId")
    List<Object> findByUserId (Integer userId);

    Optional<Keyword> findByName(String name);
}
