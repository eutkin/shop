package com.github.roman1306.shop.service;

import com.github.roman1306.shop.dao.RecordDao;
import com.github.roman1306.shop.dao.SlotDao;
import com.github.roman1306.shop.entity.User;
import com.github.roman1306.shop.exception.SlotAlreadyBookedException;
import com.github.roman1306.shop.exception.UserIsNotPatientException;
import com.github.roman1306.shop.presentation.RecordView;
import com.github.roman1306.shop.presentation.SlotView;
import com.github.roman1306.shop.request.RecordPatientRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class DefaultRecordService implements RecordService {

    @NonNull
    private final RecordDao<RecordView> recordDao;

    @NonNull
    private final SlotDao<SlotView> slotDao;

    public DefaultRecordService(
            @NonNull RecordDao<RecordView> recordDao,
            @NonNull SlotDao<SlotView> slotDao
    ) {
        this.recordDao = recordDao;
        this.slotDao = slotDao;
    }

    @NonNull
    @Override
    @Transactional
    public RecordView createRecord(@NonNull RecordPatientRequest request, User user) {
        UUID slotId = request.getSlotId();
        UUID patientId = this.recordDao.findPatientIdByUserId(user.getUsername())
                .orElseThrow(() -> new UserIsNotPatientException(user));
        boolean busy = this.recordDao.busySlot(slotId);
        if (busy) {
            throw new SlotAlreadyBookedException();
        }
        UUID recordId = this.recordDao.bookSlot(slotId, patientId);
        return Objects.requireNonNull(this.recordDao.findById(recordId));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RecordView> getMyRecords(User user, Pageable pageable) {
        return this.recordDao.findByUserId(user.getUsername(), pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SlotView> getAvailableSlots(@NonNull UUID speciality, @NonNull UUID departmentId) {
        final LocalDateTime start = LocalDateTime.now().withMinute(0).plusHours(3);
        LocalDateTime end = start.plusDays(5).withHour(20);
        return this.slotDao.findAvailable(speciality, departmentId, start, end);
    }
}
