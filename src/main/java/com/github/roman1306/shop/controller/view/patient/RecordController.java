package com.github.roman1306.shop.controller.view.patient;

import com.github.roman1306.shop.entity.User;
import com.github.roman1306.shop.presentation.DoctorView;
import com.github.roman1306.shop.presentation.RecordView;
import com.github.roman1306.shop.presentation.SlotView;
import com.github.roman1306.shop.service.ContentProvider;
import com.github.roman1306.shop.service.RecordService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.stream.Collectors.groupingBy;

@Controller
@RequestMapping({"/", "/records"})
public class RecordController {

    @NonNull
    private final RecordService recordService;

    @NonNull
    private final ContentProvider contentProvider;

    public RecordController(
            @NonNull RecordService recordService,
            @NonNull ContentProvider contentProvider
    ) {
        this.recordService = recordService;
        this.contentProvider = contentProvider;
    }

    @GetMapping
    ModelAndView myRecords(@AuthenticationPrincipal User user, Pageable pageable) {
        final var modelAndView = new ModelAndView("patient/my-record");
        Page<RecordView> myRecords = this.recordService
                .getMyRecords(user, pageable);
        modelAndView.addObject("records", myRecords);
        modelAndView.addObject("specialities", this.contentProvider.specialities());
        modelAndView.addObject("departments", this.contentProvider.departments());

        return modelAndView;
    }

    @GetMapping("/slots/{specialityId}/{departmentId}")
    ModelAndView slots(@PathVariable UUID specialityId, @PathVariable UUID departmentId) {
        final var modelAndView = new ModelAndView("patient/slots");
        final List<SlotView> slots = this.recordService.getAvailableSlots(specialityId, departmentId);

        final Map<LocalDate, Map<DoctorView, List<SlotView>>> slotsByDateAndDoctor = slots
                .stream()
                .collect(groupingBy(slot -> slot.getTime().toLocalDate(),
                        groupingBy(SlotView::getDoctor)));
        modelAndView.addObject("slots", slotsByDateAndDoctor);

        return modelAndView;
    }

}
