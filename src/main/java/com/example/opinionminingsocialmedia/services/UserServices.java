package com.example.opinionminingsocialmedia.services;

import com.example.opinionminingsocialmedia.models.User;
import com.example.opinionminingsocialmedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> result = repository.findByUsername(username);
        result.orElseThrow(() -> new UsernameNotFoundException("Not Found:" + username));
        return result.get();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {

        return NoOpPasswordEncoder.getInstance();
    }

    public void addUser(User user) {
        user.setPassword(getPasswordEncoder().encode(user.getPassword()));
        repository.save(user);
    }

    public List<User> getAllUsers() {

        return repository.findAll();
    }

    public Optional<User> findByUserName(String userName) {

        return repository.findByUsername(userName);
    }
}