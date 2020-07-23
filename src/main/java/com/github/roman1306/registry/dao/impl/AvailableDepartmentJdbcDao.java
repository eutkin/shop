package com.github.roman1306.registry.dao.impl;

import com.github.roman1306.registry.dao.DictionaryDao;
import com.github.roman1306.registry.presentation.DepartmentView;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("available-departments-dao")
public class AvailableDepartmentJdbcDao implements DictionaryDao<DepartmentView> {

    @NonNull
    private final JdbcOperations jdbc;

    @NonNull
    private final SqlHolder sqlHolder;

    public AvailableDepartmentJdbcDao(
            @NonNull JdbcOperations jdbc,
            @NonNull SqlHolder sqlHolder
    ) {
        this.jdbc = jdbc;
        this.sqlHolder = sqlHolder;
    }

    @Override
    public List<DepartmentView> load() {
        return this.jdbc.query(this.sqlHolder.load("sql/find-departments-by-slot-id.sql"), (rs, i) ->
                new DepartmentView()
                        .setId(UUID.fromString(rs.getString("department_id")))
                        .setName(rs.getString("name"))
                        .setAddress(rs.getString("address"))
                        .setImage(rs.getString("image"))
        );
    }
}
