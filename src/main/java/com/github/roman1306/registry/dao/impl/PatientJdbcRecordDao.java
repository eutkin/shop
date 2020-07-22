package com.github.roman1306.registry.dao.impl;

import com.github.roman1306.registry.dao.PatientRecordDao;
import com.github.roman1306.registry.presentation.DoctorView;
import com.github.roman1306.registry.presentation.PatientRecordView;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
public class PatientJdbcRecordDao implements PatientRecordDao<PatientRecordView> {

    @NonNull
    private final SqlHolder sqlHolder;

    @NonNull
    private final JdbcOperations jdbc;

    @NonNull
    private final RowMapper<PatientRecordView> rowMapper;

    public PatientJdbcRecordDao(
            @NonNull SqlHolder sqlHolder,
            @NonNull JdbcOperations jdbc,
            @NonNull RowMapper<PatientRecordView> rowMapper
    ) {
        this.sqlHolder = sqlHolder;
        this.jdbc = jdbc;
        this.rowMapper = rowMapper;
    }

    @Override
    public Optional<UUID> findPatientIdByUserId(String userId) {
        try {
            UUID patientId = this.jdbc.queryForObject(sql("sql/find-patient-by-user-id.sql"), (rs, i) -> {
                String raw = rs.getString(1);
                return UUID.fromString(raw);
            }, userId);
            return Optional.ofNullable(patientId);
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public UUID bookSlot(@NonNull UUID slotId, @NonNull UUID patientId) {
        UUID recordId = UUID.randomUUID();
        this.jdbc.update(sql("sql/book-slot.sql"), recordId, patientId, slotId);
        return recordId;
    }

    @Override
    public boolean busySlot(@NonNull UUID slotId) {
        //noinspection ConstantConditions Result::next dont return nullable value
        return this.jdbc.query(sql("sql/check-slot.sql"), ResultSet::next, slotId);
    }

    @Override
    public PatientRecordView findById(@NonNull UUID recordId) {
        try {
            return this.jdbc
                    .queryForObject(sql("sql/find-record-by-id.sql"), this.rowMapper, recordId);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    @NonNull
    public Page<PatientRecordView> findByUserId(@NonNull String username, @NonNull Pageable pageable) {
        final String sql = sql("sql/patient-find-records-by-user-id.sql")
                + " LIMIT " + pageable.getPageSize() + " OFFSET " + pageable.getOffset();
        final String countSql = sql("sql/patient-count-records.sql");
        final Long total = this.jdbc.queryForObject(countSql, (rs, i) -> rs.getLong("id"), username);
        final List<PatientRecordView> records = this.jdbc.query(
                sql,
                this.rowMapper,
                username
        );
        return new PageImpl<>(records, pageable, total == null ? 0 : total);
    }

    @NonNull
    private String sql(@NonNull String path) {
        return this.sqlHolder.load(path);
    }

    public static class RecordRowMapper implements RowMapper<PatientRecordView> {

        @Override
        public PatientRecordView mapRow(ResultSet rs, int rowNum) throws SQLException {
            final var timestamp = rs.getTimestamp("datetime");
            final var speciality = rs.getString("speciality");
            final var doctorName = rs.getString("doctor_name");
            final var doctorDesc = rs.getString("doctor_desc");
            final var department = rs.getString("department");
            return new PatientRecordView()
                    .setDateTime(timestamp.toLocalDateTime())
                    .setSpeciality(speciality)
                    .setDepartment(department)
                    .setDoctor(new DoctorView().setName(doctorName).setDescription(doctorDesc));
        }
    }
}
