package com.github.roman1306.registry.service.spi;

import com.github.roman1306.registry.entity.User;
import com.github.roman1306.registry.presentation.PatientRecordView;
import com.github.roman1306.registry.presentation.SlotView;
import com.github.roman1306.registry.request.RecordPatientRequest;
import io.micrometer.core.annotation.Timed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.UUID;

public interface PatientRecordService {

    @NonNull
    PatientRecordView createRecord(@NonNull RecordPatientRequest request, User user);

    @NonNull
    Page<PatientRecordView> records(@NonNull User user, Pageable pageable);

    List<SlotView> getAvailableSlots(@NonNull UUID speciality, @NonNull UUID departmentId);
}
