package com.github.roman1306.shop.controller.view.patient;

import com.github.roman1306.shop.entity.User;
import com.github.roman1306.shop.presentation.RecordPresentation;
import com.github.roman1306.shop.request.RecordPatientRequest;
import com.github.roman1306.shop.service.RecordService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class RecordController {

    private final RecordService recordService;
    private int pageSize = 20;

    public RecordController(@NonNull RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping
    ModelAndView myRecords(@AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView("patient/my-record");
        Page<RecordPresentation> myRecords = this.recordService
                .getMyRecords(user, PageRequest.of(1, this.pageSize));
        modelAndView.addObject("records", myRecords.getContent());
        return modelAndView;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
