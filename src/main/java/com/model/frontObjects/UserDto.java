package com.model.frontObjects;

import com.model.enums.RoleEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
public class UserDto {

    private Long id;

    @Pattern(regexp = "[a-zA-Z0-9\\._-]{6,25}", message = "Username has to be between 6 and 25 characters long. It can contain \"-\", \"_ \" or \".\"")
    private String username;

    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name can contain only letters")
    private String name;

    @Pattern(regexp = "[a-zA-Z0-9\\._-]{6,25}", message = "Password must be between 6 and 25 characters. It can contain \"-\", \"_ \" or \".\"")
    private String password;

    private String confirmPassword;

    @Email
    private String email;

    @Pattern(regexp = "[a-zA-Z0-9\\s\\.\\-\\\\,]{3,}", message = "Address can contain letters, numbers and \",\", \".\", \"-\" characters")
    private String address;

    private RoleEnum role;
    private Long purchaseId;
}
