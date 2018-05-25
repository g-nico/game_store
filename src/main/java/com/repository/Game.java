package com.repository;

import com.model.enums.Genre;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Game implements Serializable{

    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(mappedBy = "games")
    private List<Purchase> purchases;

    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<GameReview> gameReviews;

    @NotNull
    @Column(unique = true)
    private String name;

    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stock;

    @Min(value = 0, message = "Price cannot be negative")
    private Double price;

    private Genre genre;
    private String imgPath;

    public Game() {
        purchases = new ArrayList<>();
        gameReviews = new ArrayList<>();
    }
}
