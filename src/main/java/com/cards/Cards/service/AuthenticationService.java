package com.cards.Cards.service;

import com.cards.Cards.dto.JwtAuthenticationResponse;
import com.cards.Cards.dto.SigninRequest;
import com.cards.Cards.dto.SignupRequest;
import com.cards.Cards.dto.UpdateUserRequest;
import com.cards.Cards.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthenticationService {

    User signUp(SignupRequest signupRequest);
    JwtAuthenticationResponse signIn(SigninRequest sign);
    User updateUser(Long id, UpdateUserRequest updateUserRequest);
    User getUserById(Long id);
    Page<User> findAllByPage(Pageable pageable);

}
