package com.alexfaster.rps.service.game;

import com.alexfaster.rps.repository.GameRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DiscardGameServiceTest {

    private static final String TOKEN = "123";

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private DiscardGameService discardGameService;

    @Test
    public void verifyThatGameIsDiscardedIfPlayerExist() {
        discardGameService.discardGame(TOKEN);
        verify(gameRepository).delete(eq(TOKEN));
    }
}
