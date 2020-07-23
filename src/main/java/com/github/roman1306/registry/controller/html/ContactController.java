package com.github.roman1306.registry.controller.html;

import com.github.roman1306.registry.service.spi.ContentProvider;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Контроллер для страницы Контактов
 */
@Controller
@RequestMapping("/contacts")
public class ContactController {

    @NonNull
    private final ContentProvider contentProvider;

    public ContactController(@NonNull ContentProvider contentProvider) {
        this.contentProvider = contentProvider;
    }

    @GetMapping
    ModelAndView contacts() {
        final var modelAndView = new ModelAndView("contacts");
        modelAndView.addObject("departments", this.contentProvider.departments());
        return modelAndView;
    }

}
