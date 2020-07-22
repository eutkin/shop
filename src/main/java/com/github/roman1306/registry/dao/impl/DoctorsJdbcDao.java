package com.github.roman1306.registry.dao.impl;

import com.github.roman1306.registry.dao.DictionaryDao;
import com.github.roman1306.registry.presentation.DoctorView;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class DoctorsJdbcDao implements DictionaryDao<DoctorView> {
    @NonNull
    private final JdbcOperations jdbc;

    @NonNull
    private final SqlHolder sqlHolder;


    public DoctorsJdbcDao(@NonNull JdbcOperations jdbc, @NonNull SqlHolder sqlHolder) {
        this.jdbc = jdbc;
        this.sqlHolder = sqlHolder;
    }


    @Override
    public List<DoctorView> load() {
        return this.jdbc.query(this.sqlHolder.load("sql/find-doctors.sql"), (rs, i) ->
                new DoctorView()
                        .setId(UUID.fromString(rs.getString("doctor_id")))
                        .setName(rs.getString("name"))
                        .setDescription(rs.getString("description"))
        );
    }
}
