package com.alexfaster.rps.service.game;

import com.alexfaster.rps.repository.GameRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MakeTurnServiceTest {

    @Mock
    private GameRepository gameRepository;

    @Mock


    @InjectMocks
    private MakeTurnService makeTurnService;

    @Test
    public void verifyThatWinChangeStateCorrectly() {

    }

    @Test
    public void verifyThatLoseChangeStateCorrectly() {

    }

    @Test
    public void verifyThatDrawChangeStateCorrectly() {

    }

}
