package com.controller;

import com.model.frontObjects.LoginDto;
import com.websecurity.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class LoginLogoutController {

    @Autowired
    private SecurityService securityService;

    @PostMapping(value = "/login")
    public void login(@RequestBody LoginDto loginDto) {
        securityService.login(loginDto.getEmail(), loginDto.getPassword());
    }
}
