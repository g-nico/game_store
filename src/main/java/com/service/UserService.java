package com.service;

import com.model.frontObjects.UserDto;
import com.repository.User;
import com.repository.UserRepository;
import com.utility.UserMapper;
import com.websecurity.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User u = userRepository.findUserByEmail(s);

        if (u == null) {
            throw new UsernameNotFoundException(s);
        }

        return new LoggedUser(u.getEmail(), u.getRole());
    }
}
