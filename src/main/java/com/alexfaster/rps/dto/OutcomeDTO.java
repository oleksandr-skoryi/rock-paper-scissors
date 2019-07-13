package com.alexfaster.rps.dto;

import com.alexfaster.rps.model.Choice;
import com.alexfaster.rps.model.Outcome;
import com.alexfaster.rps.model.TurnHistory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutcomeDTO {

    private Outcome outcome;
    private Choice playerChoice;
    private Choice skynetChoice;

    public OutcomeDTO(final TurnHistory turnHistory) {
        this.outcome = turnHistory.getOutcome();
        this.playerChoice = turnHistory.getPlayerChoice();
        this.skynetChoice = turnHistory.getSkynetChoice();
    }
}
