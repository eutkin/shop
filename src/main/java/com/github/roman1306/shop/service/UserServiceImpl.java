package com.github.roman1306.shop.service;

import com.github.roman1306.shop.dao.UserDao;
import com.github.roman1306.shop.entity.Role;
import com.github.roman1306.shop.entity.User;
import com.github.roman1306.shop.exception.UserAlreadyExistsException;
import com.github.roman1306.shop.service.spi.UserService;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

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
    @NonNull
    public User register(User user) {
        final User existsUser = this.userDao.loadUserByUsername(user.getUsername());
        if (existsUser != null) {
            throw  new UserAlreadyExistsException(user.getUsername());
        }
        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        User currentUser = user.setPassword(encodedPassword);
        return this.userDao.createUser(currentUser);
    }

    @Override
    public List<Role> getRoles() {
        return this.userDao.getRoles();
    }
}
