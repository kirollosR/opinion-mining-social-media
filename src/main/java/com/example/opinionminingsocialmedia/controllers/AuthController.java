package com.example.opinionminingsocialmedia.controllers;

import com.example.opinionminingsocialmedia.core.security.JWTResponse;
import com.example.opinionminingsocialmedia.Dtos.RegisterRequest;
import com.example.opinionminingsocialmedia.models.User;
import com.example.opinionminingsocialmedia.Dtos.UserRequest;
import com.example.opinionminingsocialmedia.services.AuthServices;
import com.example.opinionminingsocialmedia.services.UserServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController()
@RequestMapping(value = "api/v1/auth")
public class AuthController {
    Logger log = LoggerFactory.getLogger(AuthController.class);
//    @Autowired
//    private TokenUtil tokenUtil;
//
    @Autowired
    private UserServices userServices;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
@Autowired
private AuthServices authServices;

//    @PostMapping(value = {"", "/login"})
//    public ResponseEntity<JWTResponse> login(@RequestBody UserRequest userRequest) throws Exception {
//        log.info("Auth login init username: " + userRequest.getUsername() + " password: " + userRequest.getPassword());
//        Authentication authentication = null;
//        try {
//            authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
//
//        } catch (DisabledException e) {
//            return new ResponseEntity(new JWTResponse(null, null, null, "USER_DISABLED"), HttpStatus.UNAUTHORIZED);
//        } catch (BadCredentialsException e) {
//            return new ResponseEntity(new JWTResponse(null, null, null, "INVALID_CREDENTIALS"), HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//             log.info("AuthError " + e.toString());
//            return new ResponseEntity(new JWTResponse(null, null, null, "Unknown_Error"), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        log.info("Auth is " + authentication.isAuthenticated());
//        if (authentication.isAuthenticated()) {
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            UserDetails userDetails = userServices.loadUserByUsername(userRequest.getUsername());
//            Optional<User> user = userServices.findByUserName(userRequest.getUsername());
//
//            String token = tokenUtil.generateToken(user.get().getId(), userDetails);
//
//            return ResponseEntity.ok(new JWTResponse(token, userDetails.getUsername(), user.get().getRole().name(), "SUCCESS"));
//        }
//        return new ResponseEntity(new JWTResponse(null, null, null, "INVALID"), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @PostMapping(value = {"", "/login"})
    public ResponseEntity<JWTResponse> login(@RequestBody UserRequest userRequest) throws Exception {
        log.info("Auth login init username: " + userRequest.getUsername() + " password: " + userRequest.getPassword());
        return ResponseEntity.ok(authServices.login(userRequest));
    }

    @PostMapping(value = {"", "/register"})
    public ResponseEntity<JWTResponse> register(@RequestBody RegisterRequest request) throws Exception {
        log.info("User info is " + request.getUsername() + " " + request.getPassword());
        final JWTResponse response = authServices.register(request);
        if(response.isSuccess()) {
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
}
