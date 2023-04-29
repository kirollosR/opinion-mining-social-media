package com.example.opinionminingsocialmedia.controllers;

import com.example.opinionminingsocialmedia.base.enums.RoleEnum;
import com.example.opinionminingsocialmedia.models.User;
import com.example.opinionminingsocialmedia.payload.requests.RegisterRequest;
import com.example.opinionminingsocialmedia.payload.requests.UserRequest;
import com.example.opinionminingsocialmedia.payload.responses.JWTResponse;
import com.example.opinionminingsocialmedia.services.AuthServices;
import com.example.opinionminingsocialmedia.services.UserServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping(value = "api/v1/auth")
public class AuthController {
    Logger log = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private UserServices userServices;
    @Autowired
    private AuthServices authServices;

    @PostMapping(value = {"", "/login"})
    public ResponseEntity<JWTResponse> login(@Valid @RequestBody UserRequest userRequest) throws Exception {
        log.info("Auth login init username: " + userRequest.getUsername() + " password: " + userRequest.getPassword());
        JWTResponse response = authServices.login(userRequest);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = {"", "/register"})
    public ResponseEntity<JWTResponse> register(@Valid @RequestBody RegisterRequest request) throws Exception {
        log.info("User info is " + request.getUsername() + " " + request.getPassword());
        final JWTResponse response = authServices.register(request, RoleEnum.USER);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = {"", "admin/register"})
    public ResponseEntity<JWTResponse> adminRegister(@Valid @RequestBody RegisterRequest request) throws Exception {
        log.info("User info is " + request.getUsername() + " " + request.getPassword());
        final JWTResponse response = authServices.register(request, RoleEnum.ADMIN);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userServices.getAllUsers();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }


    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authServices.refreshToken(request, response);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}