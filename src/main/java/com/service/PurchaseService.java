package com.service;

import com.model.frontObjects.PurchaseDto;
import com.repository.Purchase;
import com.repository.PurchaseRepository;
import com.utility.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public PurchaseDto savePurchase(PurchaseDto purchaseDto) {
        Purchase p = new Purchase();
        PurchaseMapper.toPurchase(purchaseDto, p);
        purchaseRepository.save(p);

        return purchaseDto;
    }

    public List<PurchaseDto> getAll() {
        List<Purchase> purchases = purchaseRepository.findAll();
        List<PurchaseDto> purchaseDtos = new ArrayList<>();

        purchaseDtos = PurchaseMapper.toPurchaseDtoList(purchases, purchaseDtos);
        return purchaseDtos;
    }

    public void updatePurchase(PurchaseDto purchaseDto) {
        Purchase p = purchaseRepository.findOne(purchaseDto.getId());
        PurchaseMapper.updatePurchase(purchaseDto, p);

        purchaseRepository.save(p);
    }

    public void deletePurchase(Long id) {
        purchaseRepository.delete(id);
    }
}
