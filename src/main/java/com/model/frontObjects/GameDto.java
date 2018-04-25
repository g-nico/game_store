package com.model.frontObjects;

import com.model.enums.Genre;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GameDto {

    private Long id;
    private String name;
    private Float price;
    private Boolean inStock;
    private Genre genre;
    private String imgPath;
}
