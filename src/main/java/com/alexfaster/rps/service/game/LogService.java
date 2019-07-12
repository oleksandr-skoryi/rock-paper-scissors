package com.alexfaster.rps.service.game;

import com.alexfaster.rps.model.Choice;
import com.alexfaster.rps.model.Outcome;
import com.alexfaster.rps.model.Player;
import com.alexfaster.rps.model.TurnHistory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogService {

    @Transactional
    public List<String> makeLogMessages(final Player player) {
        return player.getTurnHistory()
                .stream()
                .map(this::makeLogMessage)
                .collect(Collectors.toList());
    }

    private String makeLogMessage(final TurnHistory turnHistory) {
        final Outcome outcome = turnHistory.getOutcome();
        final Choice playerChoice = turnHistory.getPlayerChoice();
        final Choice skynetChoice = turnHistory.getSkynetChoice();
        switch (outcome) {
            case WIN: {
                return String.format(
                        "Player WINS with %s against Skynet %s",
                        playerChoice.title(),
                        skynetChoice.title()
                );
            }
            case LOSE: {
                return String.format(
                        "Player LOSES with %s against Skynet %s",
                        playerChoice.title(),
                        skynetChoice.title()
                );
            }
            case DRAW: {
                return String.format(
                        "Both pick %s, round DRAW",
                        skynetChoice.title()
                );
            }
            default:
                throw new IllegalArgumentException("Unknown outcome");
        }
    }

}
