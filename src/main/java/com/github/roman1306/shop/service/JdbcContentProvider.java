package com.github.roman1306.shop.service;

import com.github.roman1306.shop.dao.impl.SqlHolder;
import com.github.roman1306.shop.presentation.DepartmentView;
import com.github.roman1306.shop.presentation.SpecialityView;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class JdbcContentProvider implements ContentProvider {

    @NonNull
    private final JdbcOperations jdbc;

    @NonNull
    private final SqlHolder sqlHolder;

    public JdbcContentProvider(@NonNull JdbcOperations jdbc, @NonNull SqlHolder sqlHolder) {
        this.jdbc = jdbc;
        this.sqlHolder = sqlHolder;
    }

    @Override
    public List<SpecialityView> specialities() {
        return this.jdbc.query(this.sqlHolder.load("sql/find-specialities.sql"), (rs, i) ->
                new SpecialityView()
                        .setId(UUID.fromString(rs.getString("speciality_id")))
                        .setName(rs.getString("name"))
        );
    }

    @Override
    public List<DepartmentView> departments() {
        return this.jdbc.query(this.sqlHolder.load("sql/find-departments.sql"), (rs, i) ->
                new DepartmentView()
                        .setId(UUID.fromString(rs.getString("department_id")))
                        .setName(rs.getString("name"))
                        .setAddress(rs.getString("address"))
        );
    }
}
