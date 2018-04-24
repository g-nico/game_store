package com.controller;

import com.model.frontObjects.UserDto;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "getAllUsers")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "saveUser")
    public UserDto saveUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
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
