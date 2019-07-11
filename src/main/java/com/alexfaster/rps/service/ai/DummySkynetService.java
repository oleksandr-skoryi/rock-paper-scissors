package com.alexfaster.rps.service.ai;

import com.alexfaster.rps.model.Choice;
import org.springframework.stereotype.Service;

@Service
public class DummySkynetService {

    public Choice makeTurn() {
        final Choice[] values = Choice.values();
        final int randomChoice = (int)(Math.random() * values.length);
        return values[randomChoice];
    }
}
