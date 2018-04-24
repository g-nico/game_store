package com.service;

import com.model.frontObjects.UserDto;
import com.repository.User;
import com.repository.UserRepository;
import com.utility.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        userDtos = UserMapper.toUserDtoList(users, userDtos);
        return userDtos;
    }

    public void updateUser(UserDto userDto) {
        User u = userRepository.getOne(userDto.getId());
        UserMapper.updateUser(userDto, u);
        userRepository.save(u);
    }

    public UserDto addUser(UserDto userDto) {
        User u = new User();
        UserMapper.toUser(userDto, u);
        userRepository.save(u);
        return userDto;
    }

    public void deleteUser(Long id) {
        userRepository.delete(id);
    }
}
