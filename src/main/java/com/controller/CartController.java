package com.controller;

import com.model.frontObjects.CartDto;
import com.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping(value = "myCart")
    public String addToMyCart(@CookieValue("gameIds") String cookie, Model model) {
        String[] ids = cookie.split(",");
        List<Long> ids1;

        ids1 = Arrays.stream(ids)
                .map(Long::new)
                .collect(Collectors.toList());
        model.addAttribute("cartDto", cartService.getCart(ids1));

        return "myCart";
    }

    @DeleteMapping(value = "myCart/delete/{name}")
    public String deleteFromCart(@PathVariable Long id,
                                 @ModelAttribute(value = "cartDto") CartDto cartDto) {
        cartService.delete(id, cartDto);

        return "redirect:/myCart";
    }
}
