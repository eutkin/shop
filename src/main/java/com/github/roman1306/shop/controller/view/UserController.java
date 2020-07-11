package com.github.roman1306.shop.controller.view;

import com.github.roman1306.shop.entity.User;
import com.github.roman1306.shop.service.UserService;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(@NonNull UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    ModelAndView register() {
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    View register(User user) {
        User registeredUser = userService.register(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user, registeredUser.getPassword(), registeredUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/");
    }
}
