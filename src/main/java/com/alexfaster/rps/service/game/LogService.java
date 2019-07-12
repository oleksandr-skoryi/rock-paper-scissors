package com.alexfaster.rps.service.game;

import com.alexfaster.rps.model.Choice;
import com.alexfaster.rps.model.Outcome;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    public String makeLogMessage(
            final Outcome outcome,
            final Choice playerChoice,
            final Choice skynetChoice
    ) {
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
