package com.github.roman1306.shop.controller.rest;

import com.github.roman1306.shop.entity.User;
import com.github.roman1306.shop.presentation.RecordPresentation;
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

    private final RecordService recordService;

    public RecordRestController(@NonNull RecordService recordService) {
        this.recordService = recordService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    RecordPresentation recordPatient(
            @AuthenticationPrincipal User user,
            @RequestBody RecordPatientRequest request) {
        return recordService.createRecord(request);
    }

    @GetMapping
    Page<RecordPresentation> myRecords(@AuthenticationPrincipal User user, Pageable pageable) {
        return recordService.getMyRecords(user, pageable);
    }
}
