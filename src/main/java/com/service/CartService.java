package com.service;

import com.model.frontObjects.CartDto;
import com.model.frontObjects.GameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private GameService gameService;

    public CartDto getCart(final List<Long> ids) {
        CartDto cartDto = new CartDto();

        cartDto.setGames(ids.stream()
                .collect(Collectors.groupingBy(gameService::getOne, Collectors.counting())));

        return cartDto;
    }

    public void delete(Long id, CartDto cartDto) {
        //cartDto.getGames().remove(gameDto);
    }
}
