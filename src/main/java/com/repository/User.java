package com.repository;

import com.model.enums.RoleEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
public class User implements Serializable{

    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Purchase> purchases;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<GameReview> gameReviews;

    @NotNull(message = "error.password.notnull")
    @Size(min=6, max=61)
    private String password;

    @Column(unique = true)
    private String email;

    private String name;
    private String address;
    private RoleEnum role;

    public User() {
        purchases = new ArrayList<>();
        gameReviews = new HashSet<>();
    }
}
