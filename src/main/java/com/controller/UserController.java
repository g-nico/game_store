package com.controller;

import com.model.frontObjects.UserDto;
import com.service.UserService;
import com.websecurity.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

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
    public String saveUser(@ModelAttribute(value = "userDto") final UserDto userDto) {
        userService.addUser(userDto);
        return "redirect:/";
    }

    @PutMapping(value = "updateUser")
    public String updateUser(@RequestParam final Long id,
                             @ModelAttribute(value = "userDto") final UserDto userDto,
                             final HttpServletResponse response,
                             final HttpServletRequest request) {
        userService.updateUser(userDto);
        securityService.logout(request, response);

        return "redirect:/";
    }

    @PostMapping(value = "deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return "redirect:/users/getAllUsers";
    }

}
