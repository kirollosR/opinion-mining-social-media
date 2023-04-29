package com.example.opinionminingsocialmedia.repository;

import com.example.opinionminingsocialmedia.models.Role;
import com.example.opinionminingsocialmedia.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
