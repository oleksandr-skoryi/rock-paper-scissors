package com.alexfaster.rps.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Player {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Integer wins;

    @Column(nullable = false)
    private Integer loses;

    @Column(nullable = false)
    private Integer draws;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @OneToMany(
            mappedBy = "player",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @OrderBy("createdAt DESC")
    private List<TurnHistory> turnHistory;

    public Player() {
        this.wins = 0;
        this.loses = 0;
        this.draws = 0;
        this.turnHistory = new ArrayList<>();
    }

    public void incrementWins() {
        this.wins++;
    }

    public void incrementLoses() {
        this.loses++;
    }

    public void incrementDraws() {
        this.draws++;
    }

    public void addTurnHistory(final TurnHistory turn) {
        turn.setPlayer(this);
        turnHistory.add(0, turn);
    }

}
