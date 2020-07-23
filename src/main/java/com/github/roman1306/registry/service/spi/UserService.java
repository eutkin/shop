package com.github.roman1306.registry.service.spi;

import com.github.roman1306.registry.entity.Role;
import com.github.roman1306.registry.entity.User;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * Сервис пользователь. Отвечает за регистрацию.
 */
public interface UserService {

    /**
     * Региструет пользователя.
     *
     * @param user новый пользователь
     * @return зарегистрированный пользователь
     */
    @NonNull
    User register(@NonNull User user);

    /**
     * @return доступные роли
     */
    @NonNull
    List<Role> getRoles();
}
