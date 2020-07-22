package com.github.roman1306.shop.service;

import com.github.roman1306.shop.entity.Role;
import com.github.roman1306.shop.entity.User;
import org.springframework.lang.NonNull;

import java.util.List;

public interface UserService {

    @NonNull
    User register(@NonNull User user);

    @NonNull
    List<Role> getRoles();
}
