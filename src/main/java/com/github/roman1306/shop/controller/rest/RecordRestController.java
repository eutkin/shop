package com.github.roman1306.shop.controller.rest;

import com.github.roman1306.shop.entity.User;
import com.github.roman1306.shop.presentation.RecordView;
import com.github.roman1306.shop.request.RecordPatientRequest;
import com.github.roman1306.shop.service.RecordService;
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
    private final RecordService recordService;

    public RecordRestController(@NonNull RecordService recordService) {
        this.recordService = recordService;
    }

    @PostMapping
    @PreAuthorize("hasRole('PATIENT')")
    RecordView recordPatient(
            @AuthenticationPrincipal User user,
            @RequestBody RecordPatientRequest request) {
        return this.recordService.createRecord(request, user);
    }

    @GetMapping
    Page<RecordView> myRecords(@AuthenticationPrincipal User user, Pageable pageable) {
        return this.recordService.getMyRecords(user, pageable);
    }
}
