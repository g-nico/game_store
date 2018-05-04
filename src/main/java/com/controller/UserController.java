package com.controller;

import com.model.frontObjects.UserDto;
import com.service.UserService;
import com.websecurity.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/register")
    public String saveUser(@ModelAttribute(value = "userDto") UserDto userDto) {
        userService.addUser(userDto);
        return "redirect:/";
    }

    @PutMapping(value = "updateUser")
    public void updateUser(@RequestParam Long id, @RequestBody UserDto userDto) {
        userDto.setId(id);
        userService.updateUser(userDto);
    }

    @DeleteMapping(value = "deleteUser")
    public void deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
    }

}
