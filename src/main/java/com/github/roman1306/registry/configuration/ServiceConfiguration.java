package com.github.roman1306.registry.configuration;

import com.github.roman1306.registry.dao.UserDao;
import com.github.roman1306.registry.service.spi.UserService;
import com.github.roman1306.registry.service.UserServiceImpl;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootConfiguration
@Import({DaoConfiguration.class, SecurityConfiguration.class})
public class ServiceConfiguration {

    @Bean
    public UserService userService(
            @NonNull UserDao userDao,
            @NonNull PasswordEncoder passwordEncoder
    ) {
        return new UserServiceImpl(userDao, passwordEncoder);
    }
}
