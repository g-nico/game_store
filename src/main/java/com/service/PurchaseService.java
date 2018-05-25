package com.service;

import com.model.frontObjects.GameDto;
import com.model.frontObjects.PurchaseDto;
import com.repository.GameRepository;
import com.repository.Purchase;
import com.repository.PurchaseRepository;
import com.repository.UserRepository;
import com.utility.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.websecurity.SecurityService;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final GameService gameService;

    @Autowired
    public PurchaseService(final PurchaseRepository purchaseRepository,
                           final UserRepository userRepository,
                           final GameRepository gameRepository,
                           final GameService gameService) {
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.gameService = gameService;
    }

    public PurchaseDto savePurchase(PurchaseDto purchaseDto) {
        Purchase p = new Purchase();
        purchaseDto.setPurchaseDate(new Date());
        purchaseDto.setFinalPrice(purchaseDto.getGames().stream().mapToDouble(GameDto::getPrice).sum());
        PurchaseMapper.toPurchase(purchaseDto, p);

        p.setUser(userRepository.findUserByEmail(SecurityService.getLoggedUser().getEmail()));
        for(GameDto g : purchaseDto.getGames()) {
            p.getGames().add(gameRepository.findOne(g.getId()));
        }

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
