package com.alexfaster.rps.dto;

import com.alexfaster.rps.model.Player;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@Data
public class PlayerDTO {

    private StatsDTO stats;
    private List<String> history;

    public PlayerDTO(final Player player, final List<String> messages) {
        this.stats = new StatsDTO(player);
        this.history = Collections.unmodifiableList(messages);
    }
}
