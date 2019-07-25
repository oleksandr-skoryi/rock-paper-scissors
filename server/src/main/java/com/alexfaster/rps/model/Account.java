package com.alexfaster.rps.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class Account {

    @Id
    @Column(nullable = false, unique = true)
    private String token;

    @OneToOne(
            mappedBy = "account",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private Player player;

}
