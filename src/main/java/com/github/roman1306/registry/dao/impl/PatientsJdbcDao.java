package com.github.roman1306.registry.dao.impl;

import com.github.roman1306.registry.dao.DictionaryDao;
import com.github.roman1306.registry.presentation.PatientView;
import lombok.NonNull;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PatientsJdbcDao implements DictionaryDao<PatientView> {
    @NonNull
    private final JdbcOperations jdbc;

    @NonNull
    private final SqlHolder sqlHolder;

    public PatientsJdbcDao(@NonNull JdbcOperations jdbcOperations, @NonNull SqlHolder sqlHolder) {
        this.jdbc = jdbcOperations;
        this.sqlHolder = sqlHolder;
    }

    @Override
    public List<PatientView> load() {
        return this.jdbc.query(this.sqlHolder.load("sql/find-patients.sql"),(rs, i) ->
                new PatientView()
                        .setName(rs.getString("name"))
                        .setSurname(rs.getString("surname"))
                        .setBirthDate(rs.getDate("birth_date").toLocalDate()));
    }
}
