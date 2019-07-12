package com.alexfaster.rps.service.game;

import com.alexfaster.rps.repository.AccountRepository;
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
    private AccountRepository accountRepository;

    @InjectMocks
    private DiscardGameService discardGameService;

    @Test
    public void verifyThatGameIsDiscardedIfAccountExist() {
        discardGameService.discardGame(TOKEN);
        verify(accountRepository).deleteById(eq(TOKEN));
    }

    @Test
    public void verifyThatGameDiscardedGracefullyIfAccountDoesntExist() {
        discardGameService.discardGame(TOKEN);
        verify(accountRepository).deleteById(eq(TOKEN));
    }
}
