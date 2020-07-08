package com.github.roman1306.shop.configuration;

import com.github.roman1306.shop.dao.UserDao;
import com.github.roman1306.shop.dao.impl.SqlHolder;
import com.github.roman1306.shop.dao.impl.UserDaoImpl;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcOperations;

@SpringBootConfiguration
public class DaoConfiguration {

    @Bean
    SqlHolder sqlHolder() {
        return SqlHolder.inMemory();
    }

    @Bean
    UserDao userDao(JdbcOperations jdbc, SqlHolder sqlHolder) {
        return new UserDaoImpl(jdbc, sqlHolder);
    }
}
