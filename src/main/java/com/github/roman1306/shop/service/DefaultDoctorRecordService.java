package com.github.roman1306.shop.service;

import com.github.roman1306.shop.dao.RecordDao;
import com.github.roman1306.shop.entity.User;
import com.github.roman1306.shop.presentation.DoctorRecordView;
import com.github.roman1306.shop.service.spi.RecordRoleBasedStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class DefaultDoctorRecordService implements RecordRoleBasedStrategy<DoctorRecordView> {

    @NonNull
    private final RecordDao<DoctorRecordView> recordDao;

    public DefaultDoctorRecordService(@NonNull RecordDao<DoctorRecordView> recordDao) {
        this.recordDao = recordDao;
    }

    @Override
    @NonNull
    public Page<DoctorRecordView> records(@NonNull User user, @NonNull Pageable pageable) {
       return this.recordDao.findByUserId(user.getUsername(), pageable);
    }

    @Override
    public boolean support(@NonNull User user) {
        return user.isDoctor();
    }
}
