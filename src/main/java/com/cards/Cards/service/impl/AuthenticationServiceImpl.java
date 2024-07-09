package com.cards.Cards.service.impl;


import java.util.HashMap;
import java.util.Optional;

import com.cards.Cards.dto.JwtAuthenticationResponse;
import com.cards.Cards.dto.SigninRequest;
import com.cards.Cards.dto.SignupRequest;
import com.cards.Cards.dto.UpdateUserRequest;
import com.cards.Cards.entity.Role;
import com.cards.Cards.entity.User;
import com.cards.Cards.exception.ResourceNotFoundException;
import com.cards.Cards.respository.RoleRepository;
import com.cards.Cards.respository.UserRepository;
import com.cards.Cards.service.AuthenticationService;
import com.cards.Cards.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Autowired
    RoleRepository roleRepository;

    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                                     AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public User signUp(SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new RuntimeException("Username already taken");
        }
        User user = new User();
        user.setName(signupRequest.getName());
        user.setAddress(signupRequest.getAddress());
        user.setPhoneNumber(signupRequest.getPhoneNumber());
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        Optional<Role> roleOptional = roleRepository.findByName("ROLE_USER");
        if (roleOptional.isPresent()) {
            user.setRole(roleOptional.get());
        } else {
            throw new RuntimeException("Role not found");
        }
        return userRepository.save(user);

    }

    @Override
    public JwtAuthenticationResponse signIn(SigninRequest sign) {
        if (sign.getPassword() == null) {
            throw new IllegalArgumentException("Password is required");
        }
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(sign.getUsername(), sign.getPassword()));
        var user = userRepository.findByUsername(sign.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Email or Password"));
        var jwt = jwtService.generateToken(user);
//        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
//        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        jwtAuthenticationResponse.setUser(user);
        return jwtAuthenticationResponse;

    }


    @Override
    public User updateUser(Long id, UpdateUserRequest updateUserRequest) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new ResourceNotFoundException("User not found");
        }
        if (userRepository.existsByUsername(updateUserRequest.getUsername())) {
            throw new RuntimeException("Username already taken");
        }

        User user = userOptional.get();
        user.setName(updateUserRequest.getName());
        user.setAddress(updateUserRequest.getAddress());
        user.setPhoneNumber(updateUserRequest.getPhoneNumber());
        user.setUsername(updateUserRequest.getUsername());

        if (updateUserRequest.getPassword() != null && !updateUserRequest.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updateUserRequest.getPassword()));
        }

        if (updateUserRequest.getRole().getName() != null && !updateUserRequest.getRole().getName().isEmpty()) {
            Optional<Role> roleOptional = roleRepository.findByName(updateUserRequest.getRole().getName());
            if (roleOptional.isPresent()) {
                user.setRole(roleOptional.get());
            } else {
                throw new RuntimeException("Role not found");
            }
        }

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        User singleUSerOptional = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found with given id " + id));
        return singleUSerOptional;
    }

    @Override
    public Page<User> findAllByPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

}

