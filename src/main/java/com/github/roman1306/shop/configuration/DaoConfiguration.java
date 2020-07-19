package com.github.roman1306.shop.configuration;

import com.github.roman1306.shop.dao.RecordDao;
import com.github.roman1306.shop.dao.UserDao;
import com.github.roman1306.shop.dao.impl.JdbcRecordDao;
import com.github.roman1306.shop.dao.impl.SqlHolder;
import com.github.roman1306.shop.dao.impl.JdbcUserDao;
import com.github.roman1306.shop.presentation.RecordPresentation;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

@SpringBootConfiguration
public class DaoConfiguration {

    @Bean
    SqlHolder sqlHolder() {
        return SqlHolder.inMemory();
    }

    @Bean
    UserDao userDao(JdbcOperations jdbc, SqlHolder sqlHolder) {
        return new JdbcUserDao(jdbc, sqlHolder);
    }

    @Bean
    RowMapper<RecordPresentation> recordPresentationRowMapper() {
        return new JdbcRecordDao.RecordRowMapper();
    }

    @Bean
    RecordDao<RecordPresentation> recordDao(
            @NonNull SqlHolder sqlHolder,
            @NonNull JdbcOperations jdbc,
            @NonNull RowMapper<RecordPresentation> rowMapper
    ) {
        return new JdbcRecordDao(sqlHolder, jdbc, rowMapper);
    }
}
