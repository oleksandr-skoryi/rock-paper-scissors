package com.alexfaster.rps.dto;

import com.alexfaster.rps.model.Profile;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ProfileDTO {

    private Integer wins;
    private Integer loses;
    private Integer draws;
    private List<String> gameLog;

    public ProfileDTO(final Profile profile) {
        this.wins = profile.getWins();
        this.loses = profile.getLoses();
        this.draws = profile.getDraws();
        this.gameLog = profile.getGameLog();
    }
}
