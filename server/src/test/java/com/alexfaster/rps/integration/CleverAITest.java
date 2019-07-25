package com.alexfaster.rps.integration;

import com.alexfaster.rps.config.CurrentTimeConfig;
import com.alexfaster.rps.dto.TurnDTO;
import com.alexfaster.rps.model.Choice;
import com.alexfaster.rps.model.Outcome;
import com.alexfaster.rps.service.game.MakeTurnService;
import com.alexfaster.rps.service.game.StartGameService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class CleverAITest {

    @Autowired
    private StartGameService startGameService;

    @Autowired
    private MakeTurnService turnService;

    @MockBean
    private CurrentTimeConfig currentTimeConfig;

    private String token;

    @Before
    public void init() {
        this.token = startGameService.startGame().getToken();
        Mockito.when(currentTimeConfig.getTime())
                .thenReturn(LocalDateTime.now());
    }


    @Test
    public void verifyThatAITracksYourStrategy() {
        final List<Outcome> outcomes = IntStream.range(0, 10)
                .mapToObj(i -> {
                    final TurnDTO turnDTO = turnService.makeTurn(token, Choice.P);
                    return turnDTO.getOutcome().getOutcome();
                })
                .collect(Collectors.toList());

        final long winsAmount = outcomes.stream()
                .filter(outcome -> outcome == Outcome.WIN)
                .count();

        Assert.assertThat(winsAmount, not(greaterThan(1L)));

    }
}
