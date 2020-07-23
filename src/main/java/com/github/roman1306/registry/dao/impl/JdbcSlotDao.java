package com.github.roman1306.registry.dao.impl;

import com.github.roman1306.registry.dao.SlotDao;
import com.github.roman1306.registry.presentation.DoctorView;
import com.github.roman1306.registry.presentation.SlotView;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class JdbcSlotDao<T> implements SlotDao<SlotView> {

    @NonNull
    private final SqlHolder sqlHolder;

    @NonNull
    private final JdbcOperations jdbc;

    @NonNull
    private final RowMapper<SlotView> rowMapper;

    public JdbcSlotDao(
            @NonNull SqlHolder sqlHolder,
            @NonNull JdbcOperations jdbc,
            @NonNull RowMapper<SlotView> rowMapper
    ) {
        this.sqlHolder = sqlHolder;
        this.jdbc = jdbc;
        this.rowMapper = rowMapper;
    }

    @Override
    @NonNull
    public List<SlotView> findAvailable(
            @NonNull UUID speciality,
            @NonNull UUID departmentId,
            @NonNull LocalDateTime startInclusive,
            @NonNull LocalDateTime endInclusive
    ) {
        final String sql = this.sqlHolder.load("sql/available-slot.sql");
        return this.jdbc.query(sql, this.rowMapper, speciality, departmentId, startInclusive, endInclusive);
    }

    public static class SlotRowMapper implements RowMapper<SlotView> {
        @Override
        public SlotView mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new SlotView()
                    .setId(UUID.fromString(rs.getString("slot_id")))
                    .setTime(rs.getTimestamp("time_slot").toLocalDateTime())
                    .setDoctor(new DoctorView()
                            .setId(UUID.fromString(rs.getString("doctor_id")))
                            .setName(rs.getString("doctor_name"))
                            .setDescription(rs.getString("doctor_desc"))
                            .setPhoto(rs.getString("doctor_photo"))
                    )
                    .setAvailable(rs.getBoolean("available"));
        }
    }
}
