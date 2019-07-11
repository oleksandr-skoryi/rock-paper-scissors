package com.alexfaster.rps.service.game;

import com.alexfaster.rps.dto.StartGameDTO;
import com.alexfaster.rps.model.Profile;
import com.alexfaster.rps.repository.GameRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StartGameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private StartGameService startGameService;

    @Test
    public void verifyThatStartGameGeneratesToken() {
        final StartGameDTO startGameDTO = startGameService.startGame();
        assertNotNull("Token should be not null", startGameDTO.getToken());
        assertThat(startGameDTO.getToken().length(), is(36));
        verify(
                gameRepository,
                times(1)
        ).save(
                any(Profile.class)
        );
    }
}
