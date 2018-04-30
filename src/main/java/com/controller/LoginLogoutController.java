package com.controller;

import com.model.frontObjects.LoginDto;
import com.websecurity.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginLogoutController {

    @Autowired
    private SecurityService securityService;

    @PostMapping(value = "/login")
    public String login(@ModelAttribute(value = "loginDto") LoginDto loginDto) {
        securityService.login(loginDto);

        return "/";
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        model.addAttribute("loginDto", new LoginDto());

        return "fragments/login";
    }
}
