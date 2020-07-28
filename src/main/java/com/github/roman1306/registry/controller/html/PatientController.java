package com.github.roman1306.registry.controller.html;

import com.github.roman1306.registry.entity.User;
import com.github.roman1306.registry.service.spi.ContentProvider;
import lombok.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/patients")
public class PatientController {
    @NonNull
    private final ContentProvider contentProvider;

    public PatientController(@NonNull ContentProvider contentProvider) {
        this.contentProvider = contentProvider;
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping
    ModelAndView patients(@AuthenticationPrincipal User user) {
        final var modelAndView = new ModelAndView("patients");
        modelAndView.addObject("patients", this.contentProvider.patients());

        return modelAndView;
    }
}
