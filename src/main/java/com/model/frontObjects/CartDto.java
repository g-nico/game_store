package com.model.frontObjects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Component
public class CartDto {

    private List<GameDto> games;
    private String sessionId;

    public CartDto() {
        games = new ArrayList<>();
    }
}


