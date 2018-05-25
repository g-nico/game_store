package com.controller;

import com.model.frontObjects.CartDto;
import com.model.frontObjects.GameDto;
import com.model.frontObjects.PurchaseDto;
import com.repository.Game;
import com.service.CartService;
import com.service.GameService;
import com.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(value = "purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final CartService cartService;
    private final GameService gameService;

    @Autowired
    public PurchaseController(final PurchaseService purchaseService,
                              final GameService gameService,
                              final CartService cartService) {
        this.cartService = cartService;
        this.purchaseService = purchaseService;
        this.gameService = gameService;
    }

    @GetMapping(value = "savePurchase")
    public String savePurchase(@CookieValue(value = "gameIds", required = false)  String cookie) {
        final CartDto cartDto = cartService.getCart(cookie);
        final PurchaseDto purchaseDto = new PurchaseDto();

        for(Map.Entry<GameDto, Long> k : cartDto.getGames().entrySet()) {
            for(int i = 0; i < k.getValue(); i++)
                purchaseDto.getGames().add(k.getKey());
        }

        purchaseService.savePurchase(purchaseDto);
        return "/index";
    }

    @GetMapping(value = "getAllPurchases")
    public String getAllPurchases(Model model){
        model.addAttribute("purchaseDtos", purchaseService.getAll());

        return "purchases/purchasesAdminPage";
    }

    @PutMapping(value = "updatePurchase")
    public void updatePurhcase(@RequestParam Long id, @RequestBody PurchaseDto purchaseDto) {
        purchaseDto.setId(id);
        purchaseService.updatePurchase(purchaseDto);
    }

    @DeleteMapping(value = "deletePurhcase")
    public void deletePurchase(@RequestParam Long id) {
        purchaseService.deletePurchase(id);
    }

}
