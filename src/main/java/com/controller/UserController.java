package com.controller;

import com.model.frontObjects.UserDto;
import com.service.UserService;
import com.websecurity.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/myAccount")
    public String myAccount(Model model) {
        model.addAttribute("userDto", userService.getUserByEmail(SecurityService.getLoggedUser().getEmail()));
        return "users/myAccount";
    }

    @GetMapping(value = "/register")
    public String register(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "users/register";
    }

    @GetMapping(value = "getAllUsers")
    public String getAllUsers(Model model) {
        model.addAttribute("userDtos", userService.getAllUsers());

        return "users/adminUserPage";
    }

    @PostMapping(value = "/register")
    public String saveUser(@ModelAttribute(value = "userDto") UserDto userDto) {
        userService.addUser(userDto);
        return "redirect:/";
    }

    @PutMapping(value = "updateUser")
    public String updateUser(@RequestParam Long id, @ModelAttribute(value = "userDto") UserDto userDto) {
        userService.updateUser(userDto);

        return "redirect:/users/myAccount";
    }

    @DeleteMapping(value = "deleteUser")
    public void deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
    }

}
