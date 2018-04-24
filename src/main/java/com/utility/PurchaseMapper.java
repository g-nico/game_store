package com.utility;

import com.model.frontObjects.PurchaseDto;
import com.repository.Purchase;

import java.util.List;
import java.util.stream.Collectors;

public class PurchaseMapper {

    public static void toPurchaseDto(final Purchase purchase, final PurchaseDto purchaseDto) {
        purchaseDto.setId(purchase.getId());
        purchaseDto.setUserId(purchase.getUser().getId());
        purchaseDto.setPurchaseDate(purchase.getPurchaseDate());
        purchaseDto.setFinalPrice(purchase.getFinalPrice());
    }

    public static void toPurchase(final PurchaseDto purchaseDto, final Purchase purchase) {
        purchase.setId(purchaseDto.getId());
        purchase.setPurchaseDate(purchaseDto.getPurchaseDate());
        purchase.setFinalPrice(purchaseDto.getFinalPrice());
    }

    public static List<PurchaseDto> toPurchaseDtoList(final List<Purchase> purchases, List<PurchaseDto> purchaseDtos) {
        purchaseDtos = purchases.stream()
                .map(purchase -> {
                    PurchaseDto purchaseDto = new PurchaseDto();
                    PurchaseMapper.toPurchaseDto(purchase, purchaseDto);
                    return purchaseDto;
                })
                .collect(Collectors.toList());
        return purchaseDtos;
    }

    public static void updatePurchase(final PurchaseDto purchaseDto, final Purchase purchase) {
        if(purchaseDto.getPurchaseDate() != null) purchase.setPurchaseDate(purchaseDto.getPurchaseDate());
        if(purchaseDto.getFinalPrice() !=  null) purchase.setFinalPrice(purchaseDto.getFinalPrice());
    }
}
