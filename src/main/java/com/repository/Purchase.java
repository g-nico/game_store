package com.repository;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@ToString
@Entity
public class Purchase implements Serializable{

    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "game_to_purchase",
        joinColumns = @JoinColumn(name = "purchase_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"))
    private List<Game> games;

    @Temporal(TemporalType.TIMESTAMP)
    private Date purchaseDate;

    public Purchase() {
        games = new ArrayList<>();

    }

}
