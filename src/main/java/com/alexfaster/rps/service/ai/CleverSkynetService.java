package com.alexfaster.rps.service.ai;

import com.alexfaster.rps.config.SkynetConfiguration;
import com.alexfaster.rps.model.Choice;
import com.alexfaster.rps.model.Player;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CleverSkynetService implements AIBrainable {

    private final SkynetConfiguration skynetConfiguration;

    @Override
    public Choice makeTurn(final Player player) {
        final Integer chainLength = skynetConfiguration.getChainLength();
        return Choice.P;
    }
}
