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
public class LogServiceTest {

    @InjectMocks
    private LogService logService;

    @Test
    public void verifyThatWinMessageIsGeneratedCorrectly() {
        final String logMessage = logService.makeLogMessage(Outcome.WIN, Choice.P, Choice.R);
        Assert.assertThat(logMessage, is("Player WINS with PAPER against skynet ROCK"));
    }

    @Test
    public void verifyThatLoseMessageIsGeneratedCorrectly() {
        final String logMessage = logService.makeLogMessage(Outcome.LOSE, Choice.S, Choice.R);
        Assert.assertThat(logMessage, is("Skynet WINS with ROCK against Player SCISSORS"));
    }

    @Test
    public void verifyThatDrawMessageIsGeneratedCorrectly() {
        final String logMessage = logService.makeLogMessage(Outcome.DRAW, Choice.P, Choice.P);
        Assert.assertThat(logMessage, is("Both pick PAPER, round DRAW"));
    }

}
