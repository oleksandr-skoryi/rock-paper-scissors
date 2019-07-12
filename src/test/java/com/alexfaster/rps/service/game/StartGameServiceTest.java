package com.alexfaster.rps.service.game;

import com.alexfaster.rps.dto.AccountDTO;
import com.alexfaster.rps.model.Account;
import com.alexfaster.rps.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StartGameServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private StartGameService startGameService;

    @Test
    public void verifyThatStartGameGeneratesToken() {
        final AccountDTO accountDTO = startGameService.startGame();
        assertNotNull("Token should be not null", accountDTO.getToken());
        assertThat(accountDTO.getToken().length(), is(36));
        verify(
                accountRepository
        ).save(
                any(Account.class)
        );
    }
}
