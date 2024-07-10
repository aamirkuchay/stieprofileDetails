package com.cards.Cards.dto;


import com.cards.Cards.entity.User;

public class JwtAuthenticationResponse {

    private String token;
    private User user;

    public JwtAuthenticationResponse() {}
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }



    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public String toString() {
        return "JwtAuthenticationResponse [token=" + token + "]";
    }




}

