package com.alexfaster.rps.service.game;

import com.alexfaster.rps.model.Choice;
import com.alexfaster.rps.model.Outcome;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;

@RunWith(MockitoJUnitRunner.class)
public class OutcomeServiceTest {

    @InjectMocks
    private OutcomeService outcomeService;

    @Test
    public void verifyWin() {
        final Outcome outcome = outcomeService.calculatePlayerOutcome(
                Choice.P,
                Choice.R
        );
        Assert.assertThat(outcome, is(Outcome.WIN));
    }

    @Test
    public void verifyLose() {
        final Outcome outcome = outcomeService.calculatePlayerOutcome(
                Choice.S,
                Choice.R
        );
        Assert.assertThat(outcome, is(Outcome.LOSE));
    }

    @Test
    public void verifyDraw() {
        final Outcome outcome = outcomeService.calculatePlayerOutcome(
                Choice.S,
                Choice.S
        );
        Assert.assertThat(outcome, is(Outcome.DRAW));
    }

}
