package com.github.roman1306.shop.exception;

import com.github.roman1306.shop.entity.User;

public class UserIsNotPatientException extends RuntimeException {
    public UserIsNotPatientException(User user) {
    }
}
