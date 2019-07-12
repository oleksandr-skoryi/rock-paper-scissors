package com.alexfaster.rps.service.game;

import com.alexfaster.rps.config.CurrentTimeConfig;
import com.alexfaster.rps.dto.PlayerDTO;
import com.alexfaster.rps.dto.TurnDTO;
import com.alexfaster.rps.model.Account;
import com.alexfaster.rps.model.Choice;
import com.alexfaster.rps.model.Outcome;
import com.alexfaster.rps.model.Player;
import com.alexfaster.rps.repository.AccountRepository;
import com.alexfaster.rps.service.ai.Turnable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(MockitoJUnitRunner.class)
public class MakeTurnServiceTest {

    private static final String EXISTED_TOKEN = "123";

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private Turnable skynetService;

    @Mock
    private OutcomeService outcomeService;

    @Mock
    private LogService logService;

    @Mock
    private CurrentTimeConfig currentTimeConfig;

    @InjectMocks
    private MakeTurnService makeTurnService;

    @Before
    public void init() {
        final Player player = new Player();
        final Account account = new Account();
        account.setToken(EXISTED_TOKEN);
        account.setPlayer(player);
        Mockito.when(accountRepository.findById(EXISTED_TOKEN))
                .thenReturn(Optional.of(account));
        Mockito.when(skynetService.makeTurn(eq(player)))
                .thenReturn(Choice.P);
        Mockito.when(currentTimeConfig.getTime())
                .thenReturn(LocalDateTime.now());
    }

    @Test
    public void verifyThatFlowIsPassed() {
        Mockito.when(outcomeService.calculatePlayerOutcome(
                Mockito.any(Choice.class),
                Mockito.any(Choice.class)
        )).thenReturn(
                Outcome.WIN
        );
        makeTurnService.makeTurn(EXISTED_TOKEN, Choice.P);
        Mockito.verify(accountRepository).findById(eq(EXISTED_TOKEN));
        Mockito.verify(skynetService).makeTurn(any(Player.class));
        Mockito.verify(outcomeService).calculatePlayerOutcome(
                Mockito.any(Choice.class),
                Mockito.any(Choice.class)
        );
        Mockito.verify(logService).makeLogMessages(
                any(Player.class)
        );
    }


    @Test
    public void verifyThatWinChangeStateCorrectly() {
        Mockito.when(outcomeService.calculatePlayerOutcome(
                Mockito.any(Choice.class),
                Mockito.any(Choice.class)
        )).thenReturn(
                Outcome.WIN
        );
        final TurnDTO turnDTO = makeTurnService.makeTurn(EXISTED_TOKEN, Choice.P);
        Assert.assertThat(turnDTO.getOutcome(), is(Outcome.WIN));
        final PlayerDTO playerDTO = turnDTO.getProfile();
        Assert.assertThat(playerDTO.getWins(), is(1));
        Assert.assertThat(playerDTO.getLoses(), is(0));
        Assert.assertThat(playerDTO.getDraws(), is(0));
    }

    @Test
    public void verifyThatLoseChangeStateCorrectly() {
        Mockito.when(outcomeService.calculatePlayerOutcome(
                Mockito.any(Choice.class),
                Mockito.any(Choice.class)
        )).thenReturn(
                Outcome.LOSE
        );
        final TurnDTO turnDTO = makeTurnService.makeTurn(EXISTED_TOKEN, Choice.P);
        Assert.assertThat(turnDTO.getOutcome(), is(Outcome.LOSE));
        final PlayerDTO playerDTO = turnDTO.getProfile();
        Assert.assertThat(playerDTO.getWins(), is(0));
        Assert.assertThat(playerDTO.getLoses(), is(1));
        Assert.assertThat(playerDTO.getDraws(), is(0));
    }

    @Test
    public void verifyThatDrawChangeStateCorrectly() {
        Mockito.when(outcomeService.calculatePlayerOutcome(
                Mockito.any(Choice.class),
                Mockito.any(Choice.class)
        )).thenReturn(
                Outcome.DRAW
        );
        final TurnDTO turnDTO = makeTurnService.makeTurn(EXISTED_TOKEN, Choice.P);
        Assert.assertThat(turnDTO.getOutcome(), is(Outcome.DRAW));
        final PlayerDTO playerDTO = turnDTO.getProfile();
        Assert.assertThat(playerDTO.getWins(), is(0));
        Assert.assertThat(playerDTO.getLoses(), is(0));
        Assert.assertThat(playerDTO.getDraws(), is(1));
    }

}
