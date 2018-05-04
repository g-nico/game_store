package com.controller;

import com.model.frontObjects.LoginDto;
import com.websecurity.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginLogoutController {

    @Autowired
    private SecurityService securityService;

    @PostMapping(value = "/login")
    public String login(@ModelAttribute(value = "loginDto") LoginDto loginDto) {
        if(securityService.login(loginDto))
            return "redirect:/";
        else return "redirect:/myCart";
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        model.addAttribute("loginDto", new LoginDto());

        return "fragments/login";
    }

    @PostMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        securityService.logout(request, response);

        return "redirect:/";
    }
}
