package com.github.roman1306.shop.dao.impl;

import com.github.roman1306.shop.dao.RecordDao;
import com.github.roman1306.shop.presentation.RecordPresentation;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
public class JdbcRecordDao implements RecordDao<RecordPresentation> {

    @NonNull
    private final SqlHolder sqlHolder;

    @NonNull
    private final JdbcOperations jdbc;

    @NonNull
    private final RowMapper<RecordPresentation> rowMapper;

    public JdbcRecordDao(
            @NonNull SqlHolder sqlHolder,
            @NonNull JdbcOperations jdbc,
            @NonNull RowMapper<RecordPresentation> rowMapper
    ) {
        this.sqlHolder = sqlHolder;
        this.jdbc = jdbc;
        this.rowMapper = rowMapper;
    }

    @Override
    public Optional<UUID> findPatientIdByUserId(String userId) {
        try {
            UUID patientId = jdbc.queryForObject(sql("sql/find-patient-by-user-id.sql"), (rs, i) -> {
                String raw = rs.getString(1);
                return UUID.fromString(raw);
            });
            return Optional.ofNullable(patientId);
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public UUID bookSlot(@NonNull UUID slotId, @NonNull UUID patientId) {
        UUID recordId = UUID.randomUUID();
        this.jdbc.update(sql("sql/book-slot.sql"), recordId, slotId, patientId);
        return recordId;
    }

    @Override
    public boolean busySlot(@NonNull UUID slotId) {
        //noinspection ConstantConditions Result::next dont return nullable value
        return this.jdbc.query(sql("sql/check-slot.sql"), ResultSet::next);
    }

    @Override
    public RecordPresentation findById(@NonNull UUID recordId) {
        try {
            return this.jdbc.queryForObject(sql("sql/find-record-by-id.sql"), this.rowMapper);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    @NonNull
    public Page<RecordPresentation> findByUserId(@NonNull String username, @NonNull Pageable pageable) {
        final String sql = sql("sql/find-records-by-user-id.sql")
                + " LIMIT " + pageable.getPageSize() + " OFFSET " + pageable.getOffset();
        final String countSql = "select count(*) as id from records";
        final Long total = this.jdbc.queryForObject(countSql, (rs, i) -> rs.getLong("id"));
        final List<RecordPresentation> records = this.jdbc.query(
                sql,
                this.rowMapper,
                username,
                pageable.getPageSize(),
                pageable.getOffset()
        );
        return new PageImpl<>(records, pageable, total);
    }

    @NonNull
    private String sql(@NonNull String path) {
        return this.sqlHolder.load(path);
    }

    public static class RecordRowMapper implements RowMapper<RecordPresentation> {

        @Override
        public RecordPresentation mapRow(ResultSet rs, int rowNum) throws SQLException {
            final Timestamp timestamp = rs.getTimestamp("datetime");
            final String speciality = rs.getString("speciality");
            final String doctorName = rs.getString("doctor_name");
            final String doctorDesc = rs.getString("doctor_desc");
            return new RecordPresentation()
                    .setDateTime(timestamp.toLocalDateTime())
                    .setSpeciality(speciality)
                    .setDoctor(new RecordPresentation.Doctor().setName(doctorName).setDescription(doctorDesc));
        }
    }
}
