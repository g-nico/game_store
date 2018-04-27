package com.controller;

import com.model.frontObjects.PurchaseDto;
import com.service.GameService;
import com.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private GameService gameService;

    @PostMapping(value = "savePurchase")
    public PurchaseDto savePurchase(@RequestBody PurchaseDto purchaseDto) {
        return purchaseService.savePurchase(purchaseDto);
    }

    @GetMapping(value = "getAllPurchases")
    public List<PurchaseDto> getAllPurchases(){
        return purchaseService.getAll();
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

    @GetMapping(value = "myCart")
    public String addToMyCart(@CookieValue("gameIds") String cookie) {
        String[] ids = cookie.split(",");
        List<Long> ids1 = new ArrayList<>();
        for (String id : ids) {
            if (!id.equals("")) {
                ids1.add(new Long(id));
            }
        }
        return gameService.getCart(ids1).toString();
    }
}
