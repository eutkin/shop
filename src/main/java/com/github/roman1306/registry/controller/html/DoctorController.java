package com.github.roman1306.registry.controller.html;

import com.github.roman1306.registry.service.spi.ContentProvider;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/doctors")
public class DoctorController {
    @NonNull
    private final ContentProvider contentProvider;


    public DoctorController(@NonNull ContentProvider contentProvider) {
        this.contentProvider = contentProvider;
    }

    @GetMapping
    ModelAndView doctors() {
        final var modelAndView = new ModelAndView("doctors");
        modelAndView.addObject("doctors", this.contentProvider.doctors());

        return modelAndView;
    }
}
