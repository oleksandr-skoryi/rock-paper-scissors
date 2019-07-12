package com.alexfaster.rps.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class TurnHistory {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "player_choice", nullable = false)
    @Enumerated(EnumType.STRING)
    private Choice playerChoice;

    @Column(name = "skynet_choice", nullable = false)
    @Enumerated(EnumType.STRING)
    private Choice skynetChoice;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Outcome outcome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;


}
