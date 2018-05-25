package com.controller;

import com.model.frontObjects.CartDto;
import com.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(final CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(value = "myCart")
    public @ModelAttribute("cartDto")
    CartDto addToMyCart(@CookieValue(value = "gameIds", required = false)  String cookie,
                        Model model) {
        if (cookie == null || "".equals(cookie)) {
            return new CartDto();
        }

        final CartDto cartDto = cartService.getCart(cookie);
        model.addAttribute("cartDto", cartDto);

        return cartDto;
    }

    @DeleteMapping(value = "myCart/delete/{name}")
    public String deleteFromCart(@PathVariable String name,
                                 @ModelAttribute(value = "cartDto") CartDto cartDto) {

        return "redirect:/myCart";
    }
}
