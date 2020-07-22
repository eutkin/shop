package com.github.roman1306.shop.exception;

import org.springframework.lang.NonNull;

public class UserAlreadyExistsException extends RuntimeException {

    @NonNull
    private final String username;

    public UserAlreadyExistsException(@NonNull String username) {
        this.username = username;
    }

    @NonNull
    public String getUsername() {
        return username;
    }
}
