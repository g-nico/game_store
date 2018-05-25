package com.model.frontObjects;

import com.repository.Purchase;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class PurchaseDto {

    private Long id;
    private Long userId;
    private Date purchaseDate;
    private List<GameDto> games;
    private Double finalPrice;

    public PurchaseDto() {
        games = new ArrayList<>();
    }
}
