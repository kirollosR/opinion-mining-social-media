package com.example.opinionminingsocialmedia.services;

import com.example.opinionminingsocialmedia.base.enums.GenderEnum;
import com.example.opinionminingsocialmedia.base.enums.RoleEnum;
import com.example.opinionminingsocialmedia.base.enums.TokenType;
import com.example.opinionminingsocialmedia.payload.responses.JWTResponse;
import com.example.opinionminingsocialmedia.core.security.TokenUtil;
import com.example.opinionminingsocialmedia.models.*;
import com.example.opinionminingsocialmedia.payload.requests.RegisterRequest;
import com.example.opinionminingsocialmedia.payload.requests.UserRequest;
import com.example.opinionminingsocialmedia.repository.GenderRepository;
import com.example.opinionminingsocialmedia.repository.RoleRepository;
import com.example.opinionminingsocialmedia.repository.TokenRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthServices {
    @Autowired
    private UserServices userServices;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private GenderRepository genderRepository;

    Logger log = LoggerFactory.getLogger(AuthServices.class);

    public JWTResponse login(UserRequest request) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (DisabledException e) {
            return JWTResponse.builder()
                    .message("Sorry your profile is disabled")
                    .build();
        } catch (BadCredentialsException e) {
            return JWTResponse.builder()
                    .message("Email or password you entered is wrong")
                    .build();
        } catch (Exception e) {
             log.info("AuthError " + e.toString());
            return JWTResponse.builder()
                    .message("Unknown_Error")
                    .build();
        }
        var user = userServices.findByUserName(request.getUsername())
                .orElseThrow();
        var jwtToken = tokenUtil.generateToken(user);
        var refreshToken = tokenUtil.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return JWTResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .username(user.getUsername())
                .roles(user.getRole().getName())
                .message("Login Success")
                .success(true)
                .build();
    }

    public JWTResponse register(RegisterRequest request, RoleEnum roleEnum) {
        if (userServices.existsByUsername(request.getUsername())) {
            return JWTResponse.builder()
                            .message("Error: Username is already taken!")
                            .build();
        }
        var role = roleRepository.findByName(roleEnum.name());
        if(role.isEmpty()) return JWTResponse.builder()
                .message("Error: The Selected role is not found")
                .build();
        var gender = genderRepository.findById(request.getGender());
        if(gender.isEmpty()) return JWTResponse.builder()
                .message("Error: The Selected Gender is not found")
                .build();
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role.get())
                .gender(gender.get())
                .username(request.getUsername())
                .active(true)
                .build();
        userServices.addUser(user);
        var jwtToken = tokenUtil.generateToken(user);
        var refreshToken = tokenUtil.generateRefreshToken(user);
        saveUserToken(user, jwtToken);
        return JWTResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .username(user.getUsername())
                .roles(user.getRole().getName())
                .message("Register Success")
                .success(true)
                .build();
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring("Bearer ".length());
        username = tokenUtil.getUserNameFromToken(refreshToken);
        if (username != null) {
            var user = userServices.findByUserName(username)
                    .orElseThrow();
            if (tokenUtil.isTokenValid(refreshToken, user)) {
                var accessToken = tokenUtil.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = JWTResponse.builder()
                        .token(accessToken)
                        .refreshToken(refreshToken)
                        .username(user.getUsername())
                        .roles(user.getRole().getName())
                        .message("Register Success")
                        .success(true)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    public void saveAdmin(RegisterRequest request) {
        var role = roleRepository.findByName(RoleEnum.ADMIN.name());
        var gender = genderRepository.findById(request.getGender());
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role.get())
                .gender(gender.get())
                .username(request.getUsername())
                .active(true)
                .build();
        userServices.addUser(user);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
