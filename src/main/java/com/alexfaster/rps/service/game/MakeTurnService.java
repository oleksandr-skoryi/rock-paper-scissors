package com.alexfaster.rps.service.game;

import com.alexfaster.rps.dto.ProfileDTO;
import com.alexfaster.rps.dto.TurnDTO;
import com.alexfaster.rps.exception.SessionNotFoundException;
import com.alexfaster.rps.model.Choice;
import com.alexfaster.rps.model.Outcome;
import com.alexfaster.rps.model.Profile;
import com.alexfaster.rps.repository.GameRepository;
import com.alexfaster.rps.service.ai.Turnable;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MakeTurnService {

    private GameRepository gameRepository;

    private Turnable skynetService;
    private OutcomeService outcomeService;
    private LogService logService;

    public TurnDTO makeTurn(
            final String token,
            final Choice playerChoice
    ) {
        final Profile profile = gameRepository.findById(token)
                .orElseThrow(() -> new SessionNotFoundException(token));
        final Choice skynetChoice = skynetService.makeTurn();
        final Outcome outcome = outcomeService.calculatePlayerOutcome(
                playerChoice,
                skynetChoice
        );
        applyOutcome(
                profile,
                outcome
        );
        final String logMessage = logService.makeLogMessage(
                outcome,
                playerChoice,
                skynetChoice
        );
        profile.addLog(logMessage);
        return new TurnDTO(
                outcome,
                new ProfileDTO(profile)
        );
    }

    private void applyOutcome(
            final Profile profile,
            final Outcome outcome
    ) {
        if (outcome == Outcome.WIN) {
            profile.incrementWins();
        }
        if (outcome == Outcome.LOSE) {
            profile.incrementLoses();
        }
        profile.incrementDraws();
    }
}
