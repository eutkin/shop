package com.github.roman1306.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class CommonController {

    @GetMapping
    ModelAndView helloPage() {
        return new ModelAndView("welcome");
    }
}
