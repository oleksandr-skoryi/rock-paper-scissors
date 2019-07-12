package com.alexfaster.rps.service.ai;

import com.alexfaster.rps.model.Choice;
import com.alexfaster.rps.model.Player;
import org.springframework.stereotype.Service;

@Service
public class DummySkynetService implements AIBrainable {

    public Choice makeTurn(final Player player) {
        final Choice[] values = Choice.values();
        final int randomChoice = (int)(Math.random() * values.length);
        return values[randomChoice];
    }
}
