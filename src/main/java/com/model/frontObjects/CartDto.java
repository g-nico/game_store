package com.model.frontObjects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@Component
public class CartDto {

    private Map<GameDto, Long> games;

    public CartDto() {
        games = new HashMap<>();
    }
}


