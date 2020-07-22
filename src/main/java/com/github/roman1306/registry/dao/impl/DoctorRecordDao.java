package com.github.roman1306.registry.dao.impl;

import com.github.roman1306.registry.dao.RecordDao;
import com.github.roman1306.registry.presentation.DoctorRecordView;
import com.github.roman1306.registry.presentation.PatientView;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
public class DoctorRecordDao implements RecordDao<DoctorRecordView> {

    @NonNull
    private final JdbcOperations jdbc;

    @NonNull
    private final SqlHolder sqlHolder;

    @NonNull
    private final RowMapper<DoctorRecordView> rowMapper;

    public DoctorRecordDao(
            @NonNull JdbcOperations jdbc,
            @NonNull SqlHolder sqlHolder,
            @NonNull RowMapper<DoctorRecordView> rowMapper
    ) {
        this.jdbc = jdbc;
        this.sqlHolder = sqlHolder;
        this.rowMapper = rowMapper;
    }

    @Override
    @Nullable
    public DoctorRecordView findById(@NonNull UUID recordId) {
        try {
            return this.jdbc
                    .queryForObject(this.sqlHolder.load("sql/find-record-by-id.sql"), this.rowMapper, recordId);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    @NonNull
    public Page<DoctorRecordView> findByUserId(@NonNull String username, @NonNull Pageable pageable) {
        final String sql = this.sqlHolder.load("sql/doctor-find-records-by-user-id.sql")
                + " LIMIT " + pageable.getPageSize() + " OFFSET " + pageable.getOffset();
        final String countSql = this.sqlHolder.load("sql/doctor-count-records.sql");
        final Long total = this.jdbc.queryForObject(countSql, (rs, i) -> rs.getLong("id"), username);
        final List<DoctorRecordView> records = this.jdbc.query(
                sql,
                this.rowMapper,
                username
        );
        return new PageImpl<>(records, pageable, total == null ? 0 : total);
    }

    @Component
    static class DoctorRecordRowMapper implements RowMapper<DoctorRecordView> {
        @Override
        public DoctorRecordView mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new DoctorRecordView()
                    .setDateTime(rs.getTimestamp("datetime").toLocalDateTime())
                    .setDepartment(rs.getString("department"))
                    .setPatient(new PatientView()
                            .setName(rs.getString("patient_name"))
                            .setBirthDate(rs.getDate("patient_bd").toLocalDate())
                    );
        }
    }
}
