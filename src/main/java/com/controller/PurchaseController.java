package com.controller;

import com.model.frontObjects.PurchaseDto;
import com.service.GameService;
import com.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
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
