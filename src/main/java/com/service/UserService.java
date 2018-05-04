package com.service;

import com.model.enums.RoleEnum;
import com.model.frontObjects.UserDto;
import com.repository.User;
import com.repository.UserRepository;
import com.utility.UserMapper;
import com.websecurity.LoggedUser;
import com.websecurity.SecurityService;
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

    @Autowired
    private SecurityService securityService;

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

    public void addUser(UserDto userDto) {
        User u = new User();

        userDto.setRole(RoleEnum.ROLE_CLIENT);
        UserMapper.toUser(userDto, u);
        u.setPassword(securityService.encodePassword(userDto.getPassword()));
        userRepository.save(u);
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

        return new LoggedUser(u.getEmail(), u.getRole(), u.getPassword());
    }

    public UserDto getUserByEmail(String email) {
        UserDto userDto = new UserDto();
        User u = userRepository.findUserByEmail(email);

        if(u != null) {
            UserMapper.toUserDto(u, userDto);
            return userDto;
        }
        return null;
    }
}
