package com.alexfaster.rps.service.game;

import com.alexfaster.rps.dto.ProfileDTO;
import com.alexfaster.rps.dto.TurnDTO;
import com.alexfaster.rps.model.Choice;
import com.alexfaster.rps.model.Outcome;
import com.alexfaster.rps.model.Profile;
import com.alexfaster.rps.repository.GameRepository;
import com.alexfaster.rps.service.ai.Turnable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(MockitoJUnitRunner.class)
public class MakeTurnServiceTest {

    private static final String EXISTED_TOKEN = "123";

    @Mock
    private GameRepository gameRepository;

    @Mock
    private Turnable skynetService;

    @Mock
    private OutcomeService outcomeService;

    @Mock
    private LogService logService;

    @InjectMocks
    private MakeTurnService makeTurnService;

    @Before
    public void init() {
        final Profile profile = new Profile();
        profile.assignToken(EXISTED_TOKEN);
        Mockito.when(gameRepository.findById(EXISTED_TOKEN))
                .thenReturn(Optional.of(profile));
        Mockito.when(skynetService.makeTurn())
                .thenReturn(Choice.P);
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
        Mockito.verify(gameRepository).findById(eq(EXISTED_TOKEN));
        Mockito.verify(skynetService).makeTurn();
        Mockito.verify(outcomeService).calculatePlayerOutcome(
                Mockito.any(Choice.class),
                Mockito.any(Choice.class)
        );
        Mockito.verify(logService).makeLogMessage(
                Mockito.any(Outcome.class),
                Mockito.any(Choice.class),
                Mockito.any(Choice.class)
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
        final ProfileDTO profileDTO = turnDTO.getProfile();
        Assert.assertThat(profileDTO.getWins(), is(1));
        Assert.assertThat(profileDTO.getLoses(), is(0));
        Assert.assertThat(profileDTO.getDraws(), is(0));
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
        final ProfileDTO profileDTO = turnDTO.getProfile();
        Assert.assertThat(profileDTO.getWins(), is(0));
        Assert.assertThat(profileDTO.getLoses(), is(1));
        Assert.assertThat(profileDTO.getDraws(), is(0));
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
        final ProfileDTO profileDTO = turnDTO.getProfile();
        Assert.assertThat(profileDTO.getWins(), is(0));
        Assert.assertThat(profileDTO.getLoses(), is(0));
        Assert.assertThat(profileDTO.getDraws(), is(1));
    }

}
