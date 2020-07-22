package com.github.roman1306.shop.controller.html;

import com.github.roman1306.shop.entity.User;
import com.github.roman1306.shop.exception.UserAlreadyExistsException;
import com.github.roman1306.shop.service.spi.UserService;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

import static java.util.stream.Collectors.toMap;


@Controller
@RequestMapping("/")
public class UserController implements MessageSourceAware {

    private final UserService userService;
    private MessageSource messageSource;

    public UserController(@NonNull UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    ModelAndView login() {
        return new ModelAndView("login");
    }

    @GetMapping("/register")
    ModelAndView register() {
        final var modelAndView = new ModelAndView("register");
        modelAndView.addObject("roles", this.userService.getRoles());
        return modelAndView;
    }

    @PostMapping("/register")
    View register(@Validated @RequestBody User user) {
        User registeredUser = userService.register(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user, registeredUser.getPassword(), registeredUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/");
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    Map<String, String> handle(UserAlreadyExistsException ex, Locale locale) {
        final String message = this.messageSource
                .getMessage("validation.user.already-exists", new Object[]{}, locale);
        return Map.of(User.Fields.username, message);

    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    Map<String, String> handle(BindException ex, Locale locale) {
        return ex.getFieldErrors().stream()
                .filter(fe -> fe.getDefaultMessage() != null)
                .collect(toMap(FieldError::getField, s -> this.messageSource.getMessage(s.getDefaultMessage(), new Object[0], locale)));
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
