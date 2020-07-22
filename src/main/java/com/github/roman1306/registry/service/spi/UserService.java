package com.github.roman1306.registry.service.spi;

import com.github.roman1306.registry.entity.Role;
import com.github.roman1306.registry.entity.User;
import org.springframework.lang.NonNull;

import java.util.List;

public interface UserService {

    @NonNull
    User register(@NonNull User user);

    @NonNull
    List<Role> getRoles();
}
