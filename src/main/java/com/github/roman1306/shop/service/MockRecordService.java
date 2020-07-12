package com.github.roman1306.shop.service;

import com.github.roman1306.shop.entity.User;
import com.github.roman1306.shop.presentation.RecordPresentation;
import com.github.roman1306.shop.request.RecordPatientRequest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Profile("dev-frontend")
public class MockRecordService implements RecordService {

    private final List<RecordPresentation> recordPresentations = new CopyOnWriteArrayList<>();

    {
        this.recordPresentations.add(
                new RecordPresentation()
                        .setDateTime(LocalDateTime.now())
                        .setDoctor(new RecordPresentation.Doctor().setName("Василий").setDescription("Desc"))
                        .setSpeciality("Хирург")
        );
    }

    @NonNull
    @Override
    public RecordPresentation createRecord(@NonNull RecordPatientRequest request, User user) {
        return new RecordPresentation();
    }

    @Override
    public Page<RecordPresentation> getMyRecords(User user, Pageable pageable) {
        return new PageImpl<>(recordPresentations, pageable, recordPresentations.size());
    }
}
