package com.github.roman1306.shop.service;

import com.github.roman1306.shop.entity.User;
import org.springframework.lang.NonNull;

public interface UserService {

    User register(@NonNull User user);
}
