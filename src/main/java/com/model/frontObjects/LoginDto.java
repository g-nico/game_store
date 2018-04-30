package com.model.frontObjects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
public class LoginDto {

    private String email;
    private String password;
}
