package com.github.roman1306.shop.controller.html;

import com.github.roman1306.shop.entity.User;
import com.github.roman1306.shop.service.spi.ContentProvider;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
        final var modelAndView = new ModelAndView("patient/contacts");
        modelAndView.addObject("departments", this.contentProvider.departments());

        return modelAndView;
    }

}
