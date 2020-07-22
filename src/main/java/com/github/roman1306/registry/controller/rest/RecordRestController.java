package com.github.roman1306.registry.controller.rest;

import com.github.roman1306.registry.entity.User;
import com.github.roman1306.registry.presentation.PatientRecordView;
import com.github.roman1306.registry.request.RecordPatientRequest;
import com.github.roman1306.registry.service.spi.PatientRecordService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${spring.application.api-prefix:}/record")
public class RecordRestController {

    @NonNull
    private final PatientRecordService patientRecordService;

    public RecordRestController(@NonNull PatientRecordService patientRecordService) {
        this.patientRecordService = patientRecordService;
    }

    @PostMapping
    @PreAuthorize("hasRole('PATIENT')")
    PatientRecordView recordPatient(
            @AuthenticationPrincipal User user,
            @RequestBody RecordPatientRequest request) {
        return this.patientRecordService.createRecord(request, user);
    }

    @GetMapping
    Page<PatientRecordView> myRecords(@AuthenticationPrincipal User user, Pageable pageable) {
        return this.patientRecordService.records(user, pageable);
    }
}
