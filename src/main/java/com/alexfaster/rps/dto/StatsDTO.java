package com.alexfaster.rps.dto;

import com.alexfaster.rps.model.Player;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatsDTO {

    private Integer wins;
    private Integer loses;
    private Integer draws;

    public StatsDTO(final Player player) {
        this.wins = player.getWins();
        this.loses = player.getLoses();
        this.draws = player.getDraws();
    }
}
