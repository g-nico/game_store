package com.repository;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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
    private Float price;

    public Game() {
        purchases = new ArrayList<>();
    }
}
