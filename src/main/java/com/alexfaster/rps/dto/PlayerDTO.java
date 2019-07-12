package com.alexfaster.rps.dto;

import com.alexfaster.rps.model.Player;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@Data
public class PlayerDTO {

    private Integer wins;
    private Integer loses;
    private Integer draws;
    private List<String> history;

    public PlayerDTO(final Player player, final List<String> messages) {
        this.wins = player.getWins();
        this.loses = player.getLoses();
        this.draws = player.getDraws();
        this.history = Collections.unmodifiableList(messages);
    }
}
