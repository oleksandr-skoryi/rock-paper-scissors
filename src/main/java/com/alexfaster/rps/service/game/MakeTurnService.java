package com.alexfaster.rps.service.game;

import com.alexfaster.rps.config.CurrentTimeConfig;
import com.alexfaster.rps.dto.OutcomeDTO;
import com.alexfaster.rps.dto.PlayerDTO;
import com.alexfaster.rps.dto.TurnDTO;
import com.alexfaster.rps.exception.SessionNotFoundException;
import com.alexfaster.rps.model.Account;
import com.alexfaster.rps.model.Choice;
import com.alexfaster.rps.model.Outcome;
import com.alexfaster.rps.model.Player;
import com.alexfaster.rps.model.TurnHistory;
import com.alexfaster.rps.repository.AccountRepository;
import com.alexfaster.rps.service.ai.AIBrainable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MakeTurnService {

    private final AccountRepository accountRepository;

    private final AIBrainable skynetService;
    private final OutcomeService outcomeService;
    private final CurrentTimeConfig currentTimeConfig;
    private final LogService logService;

    public MakeTurnService(
            final AccountRepository accountRepository,
            @Qualifier("cleverSkynetService")
            final AIBrainable skynetService,
            final OutcomeService outcomeService,
            final CurrentTimeConfig currentTimeConfig,
            final LogService logService
    ) {
        this.accountRepository = accountRepository;
        this.skynetService = skynetService;
        this.outcomeService = outcomeService;
        this.currentTimeConfig = currentTimeConfig;
        this.logService = logService;
    }

    @Transactional
    public TurnDTO makeTurn(
            final String token,
            final Choice playerChoice
    ) {
        final Player player = accountRepository.findById(token)
                .map(Account::getPlayer)
                .orElseThrow(() -> new SessionNotFoundException(token));
        final Choice skynetChoice = skynetService.makeTurn(player);
        final Outcome outcome = outcomeService.calculatePlayerOutcome(
                playerChoice,
                skynetChoice
        );
        final TurnHistory turnHistory = new TurnHistory();
        turnHistory.setCreatedAt(currentTimeConfig.getTime());
        turnHistory.setOutcome(outcome);
        turnHistory.setPlayerChoice(playerChoice);
        turnHistory.setSkynetChoice(skynetChoice);
        player.addTurnHistory(turnHistory);
        applyOutcome(
                player,
                outcome
        );
        final OutcomeDTO outcomeDTO = new OutcomeDTO(
                outcome,
                playerChoice,
                skynetChoice
        );
        return new TurnDTO(outcomeDTO);
    }

    private void applyOutcome(
            final Player player,
            final Outcome outcome
    ) {
        if (outcome == Outcome.WIN) {
            player.incrementWins();
        }
        if (outcome == Outcome.LOSE) {
            player.incrementLoses();
        }
        if (outcome == Outcome.DRAW) {
            player.incrementDraws();
        }
    }
}
