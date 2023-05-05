package com.example.opinionminingsocialmedia.services;

import com.example.opinionminingsocialmedia.core.security.TokenUtil;
import com.example.opinionminingsocialmedia.models.User;
import com.example.opinionminingsocialmedia.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
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

    @Autowired
    private TokenUtil tokenUtil;

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

    public Boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    public boolean isUserIdValid(Integer userId) {
        User user = repository.findById(userId).orElse(null);
        return user != null;
    }

    public Boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }


    public Optional<User> findById(Integer id) {

        return repository.findById(id);
    }

    public boolean isAdmin(HttpServletRequest request) {
        String TOKEN_HEADER = "Authorization";
        final String header = request.getHeader(TOKEN_HEADER);
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring("Bearer ".length());
            String username = tokenUtil.getUserNameFromToken(token);
            Integer roleId = repository.findRoleIdById(username);
            return roleId == 1;
        } else {
            return false;
        }
    }
}
