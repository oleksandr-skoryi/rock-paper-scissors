package com.alexfaster.rps.service.ai;

import com.alexfaster.rps.config.SkynetConfiguration;
import com.alexfaster.rps.model.Choice;
import com.alexfaster.rps.model.Player;
import com.alexfaster.rps.model.TurnHistory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CleverSkynetService implements AIBrainable {

    private final SkynetConfiguration skynetConfiguration;

    @Override
    public Choice makeTurn(final Player player) {
        final Integer chainLength = skynetConfiguration.getChainLength();
        final List<Choice> playerChoices = player.getTurnHistory()
                .stream()
                .map(TurnHistory::getPlayerChoice)
                .limit(chainLength)
                .collect(Collectors.toList());
        return Choice.P;
    }
}
