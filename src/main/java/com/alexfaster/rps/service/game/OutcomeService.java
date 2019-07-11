package com.alexfaster.rps.service.game;

import com.alexfaster.rps.model.Choice;
import com.alexfaster.rps.model.Outcome;
import org.springframework.stereotype.Service;

@Service
public class OutcomeService {

    public Outcome calculatePlayerOutcome(
            final Choice playerChoice,
            final Choice skynetChoice
    ) {
        final Choice.ChoiceComparator choiceComparator = new Choice.ChoiceComparator();
        final int compare = choiceComparator.compare(
                playerChoice,
                skynetChoice
        );
        switch (compare) {
            case 1: return Outcome.WIN;
            case -1: return Outcome.LOSE;
            case 0: return Outcome.DRAW;
            default: throw new IllegalArgumentException("Unexpected outcome");
        }
    }
}
