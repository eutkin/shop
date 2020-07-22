package com.github.roman1306.shop.configuration;

import com.github.roman1306.shop.dao.PatientRecordDao;
import com.github.roman1306.shop.dao.RecordDao;
import com.github.roman1306.shop.dao.SlotDao;
import com.github.roman1306.shop.dao.UserDao;
import com.github.roman1306.shop.dao.impl.PatientJdbcRecordDao;
import com.github.roman1306.shop.dao.impl.JdbcSlotDao;
import com.github.roman1306.shop.dao.impl.SqlHolder;
import com.github.roman1306.shop.dao.impl.JdbcUserDao;
import com.github.roman1306.shop.presentation.PatientRecordView;
import com.github.roman1306.shop.presentation.SlotView;
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
    RowMapper<PatientRecordView> recordPresentationRowMapper() {
        return new PatientJdbcRecordDao.RecordRowMapper();
    }

    @Bean
    PatientRecordDao<PatientRecordView> recordDao(
            @NonNull SqlHolder sqlHolder,
            @NonNull JdbcOperations jdbc,
            @NonNull RowMapper<PatientRecordView> rowMapper
    ) {
        return new PatientJdbcRecordDao(sqlHolder, jdbc, rowMapper);
    }

    @Bean
    RowMapper<SlotView> slotViewRowMapper() {
        return new JdbcSlotDao.SlotRowMapper();
    }

    @Bean
    SlotDao<SlotView> slotDao(
            @NonNull SqlHolder sqlHolder,
            @NonNull JdbcOperations jdbc,
            @NonNull RowMapper<SlotView> rowMapper
    ) {
        return new JdbcSlotDao<>(sqlHolder, jdbc, rowMapper);
    }
}
