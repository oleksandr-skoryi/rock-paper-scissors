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
    public void verifyWinPR() {
        final Outcome outcome = outcomeService.calculatePlayerOutcome(
                Choice.P,
                Choice.R
        );
        Assert.assertThat(outcome, is(Outcome.WIN));
    }

    @Test
    public void verifyWinRS() {
        final Outcome outcome = outcomeService.calculatePlayerOutcome(
                Choice.R,
                Choice.S
        );
        Assert.assertThat(outcome, is(Outcome.WIN));
    }

    @Test
    public void verifyWinSP() {
        final Outcome outcome = outcomeService.calculatePlayerOutcome(
                Choice.S,
                Choice.P
        );
        Assert.assertThat(outcome, is(Outcome.WIN));
    }

    @Test
    public void verifyLoseSR() {
        final Outcome outcome = outcomeService.calculatePlayerOutcome(
                Choice.S,
                Choice.R
        );
        Assert.assertThat(outcome, is(Outcome.LOSE));
    }

    @Test
    public void verifyLoseRP() {
        final Outcome outcome = outcomeService.calculatePlayerOutcome(
                Choice.R,
                Choice.P
        );
        Assert.assertThat(outcome, is(Outcome.LOSE));
    }

    @Test
    public void verifyLosePS() {
        final Outcome outcome = outcomeService.calculatePlayerOutcome(
                Choice.P,
                Choice.S
        );
        Assert.assertThat(outcome, is(Outcome.LOSE));
    }

    @Test
    public void verifyDrawSS() {
        final Outcome outcome = outcomeService.calculatePlayerOutcome(
                Choice.S,
                Choice.S
        );
        Assert.assertThat(outcome, is(Outcome.DRAW));
    }

    @Test
    public void verifyDrawPP() {
        final Outcome outcome = outcomeService.calculatePlayerOutcome(
                Choice.P,
                Choice.P
        );
        Assert.assertThat(outcome, is(Outcome.DRAW));
    }

    @Test
    public void verifyDrawRR() {
        final Outcome outcome = outcomeService.calculatePlayerOutcome(
                Choice.R,
                Choice.R
        );
        Assert.assertThat(outcome, is(Outcome.DRAW));
    }

}
