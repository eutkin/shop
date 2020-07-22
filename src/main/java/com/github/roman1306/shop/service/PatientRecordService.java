package com.github.roman1306.shop.service;

import com.github.roman1306.shop.entity.User;
import com.github.roman1306.shop.presentation.PatientRecordView;
import com.github.roman1306.shop.presentation.SlotView;
import com.github.roman1306.shop.request.RecordPatientRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.UUID;

public interface PatientRecordService {

    @NonNull
    PatientRecordView createRecord(@NonNull RecordPatientRequest request, User user);

    @NonNull
    Page<PatientRecordView> getMyRecords(@NonNull User user, Pageable pageable);

    List<SlotView> getAvailableSlots(@NonNull UUID speciality, @NonNull UUID departmentId);
}
