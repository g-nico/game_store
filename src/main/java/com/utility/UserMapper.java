package com.utility;

import com.model.frontObjects.UserDto;
import com.repository.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static void toUserDto(final User user,final UserDto userDto) {
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        userDto.setAddress(user.getAddress());
        userDto.setRole(user.getRole());
    }

    public static void toUser(final UserDto userDto, final User user) {
        user.setId(userDto.getId());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setAddress(userDto.getAddress());
        user.setRole(userDto.getRole());
    }

    public static List<UserDto> toUserDtoList(final List<User> users, List<UserDto> userDtos) {
        userDtos = users.stream()
                .map(user -> {
                    UserDto userDto = new UserDto();
                    UserMapper.toUserDto(user, userDto);
                    return userDto;
                })
                .collect(Collectors.toList());
        return userDtos;
    }

    public static void updateUser(final UserDto userDto, final User toBeUpdated) {
        if(userDto.getName() != null) toBeUpdated.setName(userDto.getName());
        if((userDto.getPassword() != null) && (!userDto.getPassword().equals(""))) toBeUpdated.setPassword(userDto.getPassword());
        if(userDto.getEmail() != null) toBeUpdated.setEmail(userDto.getEmail());
        if(userDto.getAddress() != null) toBeUpdated.setAddress(userDto.getAddress());
        if(userDto.getRole() != null) toBeUpdated.setRole(userDto.getRole());
    }
}
