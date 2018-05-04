package com.controller;

import com.model.frontObjects.UserDto;
import com.service.GameService;
import com.service.UserService;
import com.websecurity.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private GameService gameService;

    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("gameDtos", gameService.getAll());
        return "/index";
    }
}
