package com.alexfaster.rps.service.game;

import com.alexfaster.rps.dto.ProfileDTO;
import com.alexfaster.rps.exception.SessionNotFoundException;
import com.alexfaster.rps.model.Profile;
import com.alexfaster.rps.repository.GameRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(MockitoJUnitRunner.class)
public class StatsServiceTest {

    private static final String EXISTED_TOKEN = "123";
    private static final String INVALID_TOKEN = "987";

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private StatsService statsService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void init() {
        final Profile p = new Profile();
        p.assignToken(EXISTED_TOKEN);
        p.addLog("Dummy Log 1");
        p.addLog("Dummy Log 2");
        Mockito.when(
                gameRepository.findById(anyString())
        ).thenReturn(Optional.empty());
        Mockito.when(
                gameRepository.findById(eq(EXISTED_TOKEN))
        ).thenReturn(Optional.of(p));
    }

    @Test
    public void verifyThatStatsAssembled() {
        final ProfileDTO stats = statsService.getStats(EXISTED_TOKEN);
        Assert.assertThat(stats.getGameLog().size(), is(2));
        Assert.assertThat(stats.getGameLog(), is(Arrays.asList(
                "Dummy Log 1",
                "Dummy Log 2"
        )));
    }

    @Test
    public void verifyThatStatsNotFoundForBadToken() {
        expectedException.expect(SessionNotFoundException.class);
        statsService.getStats(INVALID_TOKEN);
    }

}
