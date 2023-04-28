package com.example.opinionminingsocialmedia.repository;

import com.example.opinionminingsocialmedia.models.Gender;
import com.example.opinionminingsocialmedia.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenderRepository extends JpaRepository<Gender, Integer> {
    Optional<Gender> findByName(String name);
}
