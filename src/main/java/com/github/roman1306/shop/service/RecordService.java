package com.github.roman1306.shop.service;

import com.github.roman1306.shop.entity.User;
import com.github.roman1306.shop.presentation.RecordPresentation;
import com.github.roman1306.shop.request.RecordPatientRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

public interface RecordService {

    @NonNull
    RecordPresentation createRecord(@NonNull RecordPatientRequest request, User user);

    @NonNull
    Page<RecordPresentation> getMyRecords(@NonNull User user, Pageable pageable);
}
