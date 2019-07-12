package com.alexfaster.rps.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Profile {

    private String token;
    private Integer wins;
    private Integer loses;
    private Integer draws;
    private List<String> gameLog;

    public Profile() {
        this.wins = 0;
        this.loses = 0;
        this.draws = 0;
        this.gameLog = new ArrayList<>();
    }

    public void assignToken(final String token) {
        this.token = token;
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

    public void addLog(final String log) {
        gameLog.add(0, log);
    }

    public List<String> getGameLog() {
        return Collections.unmodifiableList(gameLog);
    }

}
