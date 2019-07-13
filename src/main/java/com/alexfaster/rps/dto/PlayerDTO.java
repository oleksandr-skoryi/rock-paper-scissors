package com.alexfaster.rps.dto;

import com.alexfaster.rps.model.Player;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
public class PlayerDTO {

    private StatsDTO stats;
    private List<OutcomeDTO> history;

    public PlayerDTO(final Player player) {
        this.stats = new StatsDTO(player);
        this.history = player.getTurnHistory()
                .stream()
                .map(OutcomeDTO::new)
                .collect(Collectors.toList());
    }
}
