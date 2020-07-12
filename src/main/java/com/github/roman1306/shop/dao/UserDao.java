package com.github.roman1306.shop.dao;

import com.github.roman1306.shop.entity.User;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDao extends UserDetailsService {

    @Override
    User loadUserByUsername(String s) throws UsernameNotFoundException;

    @NonNull
    User createUser(@NonNull User user);

}