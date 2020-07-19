package com.github.roman1306.shop.service;

import com.github.roman1306.shop.dao.RecordDao;
import com.github.roman1306.shop.entity.User;
import com.github.roman1306.shop.exception.SlotAlreadyBookedException;
import com.github.roman1306.shop.exception.UserIsNotPatientException;
import com.github.roman1306.shop.presentation.RecordPresentation;
import com.github.roman1306.shop.request.RecordPatientRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DefaultRecordService implements RecordService {

    private final RecordDao<RecordPresentation> recordDao;

    public DefaultRecordService(@NonNull RecordDao<RecordPresentation> recordDao) {
        this.recordDao = recordDao;
    }

    @NonNull
    @Override
    @Transactional
    public RecordPresentation createRecord(@NonNull RecordPatientRequest request, User user) {
        UUID slotId = request.getSlotId();
        UUID patientId = this.recordDao.findPatientIdByUserId(user.getUsername())
                .orElseThrow(() -> new UserIsNotPatientException(user));
        boolean busy = this.recordDao.busySlot(slotId);
        if (busy) {
            throw new SlotAlreadyBookedException();
        }
        UUID recordId = this.recordDao.bookSlot(slotId, patientId);
        return this.recordDao.findById(recordId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RecordPresentation> getMyRecords(User user, Pageable pageable) {
        return this.recordDao.findByUserId(user.getUsername(), pageable);
    }
}
