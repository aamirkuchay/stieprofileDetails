package com.cards.Cards.controller;


import com.cards.Cards.dto.JwtAuthenticationResponse;
import com.cards.Cards.dto.SigninRequest;
import com.cards.Cards.dto.SignupRequest;
import com.cards.Cards.entity.User;
import com.cards.Cards.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignupRequest signupRequest) {
        return ResponseEntity.ok(authenticationService.signUp(signupRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SigninRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }


}

