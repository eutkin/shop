package com.github.roman1306.registry.dao;

import com.github.roman1306.registry.entity.Role;
import com.github.roman1306.registry.entity.User;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserDao extends UserDetailsService {

    @Override
    User loadUserByUsername(String s) throws UsernameNotFoundException;

    @NonNull
    User createUser(@NonNull User user);

    @Transactional(readOnly = true)
    List<Role> getRoles();
}
