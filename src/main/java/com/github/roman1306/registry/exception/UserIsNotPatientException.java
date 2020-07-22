package com.github.roman1306.registry.exception;

import com.github.roman1306.registry.entity.User;

public class UserIsNotPatientException extends RuntimeException {
    public UserIsNotPatientException(User user) {
    }
}
