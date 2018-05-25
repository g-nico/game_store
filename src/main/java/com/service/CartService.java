package com.service;

import com.model.frontObjects.CartDto;
import com.model.frontObjects.GameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final GameService gameService;

    @Autowired
    public CartService(final GameService gameService) {
        this.gameService = gameService;
    }

    public CartDto getCart(final String cookie) {
        String[] ids = cookie.split(",");
        List<Long> ids1;

        ids1 = Arrays.stream(ids)
                .map(id -> {
                    id = id.replaceAll("\\s", "");
                    return id;
                })
                .map(Long::new)
                .collect(Collectors.toList());

        CartDto cartDto = new CartDto();
        boolean found = false;

        for (Long id : ids1) {
            GameDto g = gameService.getOne(id);
            for (GameDto gd : cartDto.getGames().keySet()) {
                if (gd.getId().equals(id)) {
                    found = true;
                    Long val = cartDto.getGames().get(gd) + 1;
                    cartDto.getGames().put(gd, val);
                }
            }
            if (!found) {
                cartDto.getGames().put(g, 1L);
            }
            found = false;
        }

        return cartDto;
    }

    public void delete(String name, CartDto cartDto) {
        for (GameDto g : cartDto.getGames().keySet()) {
            if (name.equals(g.getName())) {
                cartDto.getGames().remove(g);
                break;
            }
        }
    }
}
