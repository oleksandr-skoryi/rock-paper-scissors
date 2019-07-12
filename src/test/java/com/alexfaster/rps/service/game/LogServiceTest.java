package com.alexfaster.rps.service.game;

import com.alexfaster.rps.model.Choice;
import com.alexfaster.rps.model.Outcome;
import com.alexfaster.rps.model.Player;
import com.alexfaster.rps.model.TurnHistory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;

@RunWith(MockitoJUnitRunner.class)
public class LogServiceTest {

    @InjectMocks
    private LogService logService;

    @Test
    public void verifyThatWinMessageIsGeneratedCorrectly() {
        final Player player = new Player();
        final TurnHistory turnHistory = new TurnHistory();
        turnHistory.setOutcome(Outcome.WIN);
        turnHistory.setPlayerChoice(Choice.P);
        turnHistory.setSkynetChoice(Choice.R);
        player.addTurnHistory(turnHistory);
        final List<String> logMessages = logService.makeLogMessages(player);
        Assert.assertFalse(logMessages.isEmpty());
        Assert.assertThat(logMessages.get(0), is("Player WINS with PAPER against Skynet ROCK"));
    }

    @Test
    public void verifyThatLoseMessageIsGeneratedCorrectly() {
        final Player player = new Player();
        final TurnHistory turnHistory = new TurnHistory();
        turnHistory.setOutcome(Outcome.LOSE);
        turnHistory.setPlayerChoice(Choice.S);
        turnHistory.setSkynetChoice(Choice.R);
        player.addTurnHistory(turnHistory);

        final List<String> logMessages = logService.makeLogMessages(player);
        Assert.assertFalse(logMessages.isEmpty());
        Assert.assertThat(logMessages.get(0), is("Player LOSES with SCISSORS against Skynet ROCK"));
    }

    @Test
    public void verifyThatDrawMessageIsGeneratedCorrectly() {
        final Player player = new Player();
        final TurnHistory turnHistory = new TurnHistory();
        turnHistory.setOutcome(Outcome.DRAW);
        turnHistory.setPlayerChoice(Choice.P);
        turnHistory.setSkynetChoice(Choice.P);
        player.addTurnHistory(turnHistory);
        final List<String> logMessages = logService.makeLogMessages(player);
        Assert.assertFalse(logMessages.isEmpty());
        Assert.assertThat(logMessages.get(0), is("Both pick PAPER, round DRAW"));
    }

}
