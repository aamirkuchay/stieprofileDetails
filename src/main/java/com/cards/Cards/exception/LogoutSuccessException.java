package com.cards.Cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class LogoutSuccessException extends RuntimeException {
    public LogoutSuccessException(String message) {
        super(message);
    }
}
