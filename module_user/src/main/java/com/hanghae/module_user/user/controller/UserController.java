package com.hanghae.module_user.user.controller;

import com.hanghae.module_user.security.UserDetailsImpl;
import com.hanghae.module_user.user.dto.request.LoginRequest;
import com.hanghae.module_user.user.dto.request.CreateUserRequest;
import com.hanghae.module_user.user.dto.request.VerificationRequest;
import com.hanghae.module_user.user.dto.response.LoginResponse;
import com.hanghae.module_user.user.dto.response.CreateUserResponse;
import com.hanghae.module_user.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/users")
    public ResponseEntity<CreateUserResponse> signUp(@RequestBody CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(userService.create(createUserRequest));
    }

    @PostMapping("/api/verification")
    public ResponseEntity<Void> verifyEmail(@RequestBody VerificationRequest verificationRequest) {
        log.info("이메일 인증 시작");
        String email = verificationRequest.getEmail();
        userService.verifyEmail(email);
        log.info("이메일 인증 끝");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        LoginResponse loginResponse = userService.login(loginRequest);

        response.setHeader("access-Token", loginResponse.getAccessToken());
        response.setHeader("refresh-Token", loginResponse.getRefreshToken());
        return ResponseEntity.ok().body("Login successful");
    }

    @PostMapping("/api/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.substring(7);
        userService.logout(token);
        return ResponseEntity.ok().body("logout success");
    }


//    @PutMapping("/api/users/{id}")
//    public ResponseEntity<CreateUserResponse> update(@PathVariable Long id,
//                                                     @RequestBody CreateUserRequest createUserRequest,
//                                                     @AuthenticationPrincipal UserDetailsImpl userDetails
//    ) {
//        Long userId = userDetails.getId();
//        return ResponseEntity.ok(userService.update(id, userId, createUserRequest));
//    }

}
