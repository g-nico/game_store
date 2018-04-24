package com.controller;

import com.model.frontObjects.PurchaseDto;
import com.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

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
}