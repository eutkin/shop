package com.github.roman1306.shop.dao.impl;

import com.github.roman1306.shop.dao.DictionaryDao;
import com.github.roman1306.shop.presentation.DepartmentView;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class DepartmentJdbcDao implements DictionaryDao<DepartmentView> {

    @NonNull
    private final JdbcOperations jdbc;

    @NonNull
    private final SqlHolder sqlHolder;

    public DepartmentJdbcDao(
            @NonNull JdbcOperations jdbc,
            @NonNull SqlHolder sqlHolder
    ) {
        this.jdbc = jdbc;
        this.sqlHolder = sqlHolder;
    }

    @Override
    public List<DepartmentView> load() {
        return this.jdbc.query(this.sqlHolder.load("sql/find-departments.sql"), (rs, i) ->
                new DepartmentView()
                        .setId(UUID.fromString(rs.getString("department_id")))
                        .setName(rs.getString("name"))
                        .setAddress(rs.getString("address"))
        );
    }
}
