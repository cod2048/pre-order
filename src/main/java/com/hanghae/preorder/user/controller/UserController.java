package com.hanghae.preorder.user.controller;

import com.hanghae.preorder.user.dto.request.UserRequest;
import com.hanghae.preorder.user.dto.request.VerificationRequest;
import com.hanghae.preorder.user.dto.response.UserResponse;
import com.hanghae.preorder.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/users")
    public ResponseEntity<UserResponse> signUp(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.create(userRequest));
    }

    @PostMapping("/api/verification")
    public ResponseEntity<Void> verifyEmail(@RequestBody VerificationRequest verificationRequest){
        String email = verificationRequest.getEmail();
        userService.verifyEmail(email);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id,
                                               @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.update(id, userRequest));
    }
}
