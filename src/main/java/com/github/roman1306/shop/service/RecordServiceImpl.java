package com.github.roman1306.shop.service;

import com.github.roman1306.shop.entity.User;
import com.github.roman1306.shop.presentation.RecordPresentation;
import com.github.roman1306.shop.request.RecordPatientRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {

    @NonNull
    @Override
    public RecordPresentation createRecord(@NonNull RecordPatientRequest request) {
        return null;
    }

    @Override
    public Page<RecordPresentation> getMyRecords(User user, Pageable pageable) {
        return Page.empty();
    }
}
