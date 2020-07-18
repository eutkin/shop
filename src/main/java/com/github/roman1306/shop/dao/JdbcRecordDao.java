package com.github.roman1306.shop.dao;

import com.github.roman1306.shop.presentation.RecordPresentation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public class JdbcRecordDao implements RecordDao {
    @Override
    public Optional<UUID> findPatientIdByUserId(String userId) {
        return Optional.empty();
    }

    @Override
    public UUID bookSlot(UUID slotId, UUID patientId) {
        return null;
    }

    @Override
    public boolean checkSlot(UUID slotId) {
        return false;
    }

    @Override
    public RecordPresentation findById(UUID recordId) {
        return null;
    }

    @Override
    public Page<RecordPresentation> findByUserId(String username, Pageable pageable) {
        return null;
    }
}
