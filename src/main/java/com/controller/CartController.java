package com.controller;

import com.model.frontObjects.CartDto;
import com.model.frontObjects.GameDto;
import com.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping(value = "myCart")
    public @ModelAttribute("cartDto") CartDto addToMyCart(@CookieValue("gameIds") String cookie, Model model) {
        String[] ids = cookie.split(",");
        List<Long> ids1;

        ids1 = Arrays.stream(ids)
                .map(id -> {
                    id.replaceAll("\\s", "");
                    return id;
                })
                .map(Long::new)
                .collect(Collectors.toList());
        return cartService.getCart(ids1);
    }

    @DeleteMapping(value = "myCart/delete/{name}")
    public String deleteFromCart(@PathVariable String name,
                                 @ModelAttribute(value = "cartDto") CartDto cartDto) {
        //cartService.delete(name, games);

        return "redirect:/myCart";
    }
}
