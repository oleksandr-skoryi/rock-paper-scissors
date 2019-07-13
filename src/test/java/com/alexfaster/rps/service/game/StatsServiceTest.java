package com.alexfaster.rps.service.game;

import com.alexfaster.rps.dto.PlayerDTO;
import com.alexfaster.rps.exception.SessionNotFoundException;
import com.alexfaster.rps.model.Account;
import com.alexfaster.rps.model.Player;
import com.alexfaster.rps.model.TurnHistory;
import com.alexfaster.rps.repository.AccountRepository;
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
    private AccountRepository accountRepository;

    @InjectMocks
    private StatsService statsService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void init() {
        final Player p = new Player();
        final Account a = new Account();
        a.setToken(EXISTED_TOKEN);
        a.setPlayer(p);
        p.addTurnHistory(Mockito.mock(TurnHistory.class));
        p.addTurnHistory(Mockito.mock(TurnHistory.class));

        Mockito.when(
                accountRepository.findById(anyString())
        ).thenReturn(Optional.empty());

        Mockito.when(
                accountRepository.findById(eq(EXISTED_TOKEN))
        ).thenReturn(Optional.of(a));
    }

    @Test
    public void verifyThatStatsAssembled() {
        final PlayerDTO stats = statsService.getStats(EXISTED_TOKEN);
        Assert.assertThat(stats.getHistory().size(), is(2));
    }

    @Test
    public void verifyThatStatsNotFoundForBadToken() {
        expectedException.expect(SessionNotFoundException.class);
        statsService.getStats(INVALID_TOKEN);
    }

}
