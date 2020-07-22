package com.github.roman1306.registry.dao.impl;

import com.github.roman1306.registry.dao.DictionaryDao;
import com.github.roman1306.registry.presentation.SpecialityView;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class SpecialitiesJdbcDao implements DictionaryDao<SpecialityView> {

    @NonNull
    private final JdbcOperations jdbc;

    @NonNull
    private final SqlHolder sqlHolder;

    public SpecialitiesJdbcDao(@NonNull JdbcOperations jdbc, @NonNull SqlHolder sqlHolder) {
        this.jdbc = jdbc;
        this.sqlHolder = sqlHolder;
    }

    @Override
    public List<SpecialityView> load() {
        return this.jdbc.query(this.sqlHolder.load("sql/find-specialities.sql"), (rs, i) ->
                new SpecialityView()
                        .setId(UUID.fromString(rs.getString("speciality_id")))
                        .setName(rs.getString("name"))
        );
    }
}
