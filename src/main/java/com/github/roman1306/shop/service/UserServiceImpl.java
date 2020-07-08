package com.github.roman1306.shop.service;

import com.github.roman1306.shop.dao.UserDao;
import com.github.roman1306.shop.entity.Role;
import com.github.roman1306.shop.entity.User;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            @NonNull UserDao userDao,
            @NonNull PasswordEncoder passwordEncoder
    ) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        User currentUser = user.addRole(new Role().setName("CUSTOMER")).setPassword(encodedPassword);
        return this.userDao.createUser(currentUser);
    }
}
