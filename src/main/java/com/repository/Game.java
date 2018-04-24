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

    @NotNull
    @Column(unique = true)
    private String name;

    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stock;

    @Min(value = 0, message = "Price cannot be negative")
    private Float price;

    private Genre genre;

    public Game() {
        purchases = new ArrayList<>();
    }
}
