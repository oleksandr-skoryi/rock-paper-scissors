package com.alexfaster.rps.integration;

import com.alexfaster.rps.dto.AccountDTO;
import com.alexfaster.rps.dto.PlayerDTO;
import com.alexfaster.rps.dto.StatsDTO;
import com.alexfaster.rps.dto.TurnDTO;
import com.alexfaster.rps.dto.UserChoiceDTO;
import com.alexfaster.rps.model.Choice;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class EndToEndGameTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void verifyThatGameFlowWorksFine() {

        final String gameToken = testAccountCreation();

        final HttpHeaders headers = new HttpHeaders();
        headers.set("Game-Token", gameToken);

        testMakeTurn(headers);

        testGetStats(headers);

        testDiscardGame(headers);

    }

    private String testAccountCreation() {
        final AccountDTO accountDTO = this.restTemplate.exchange(
                "http://localhost:" + port + "/game/start",
                HttpMethod.POST,
                null,
                AccountDTO.class
        ).getBody();

        Assert.assertNotNull(accountDTO);
        final String gameToken = accountDTO.getToken();
        Assert.assertNotNull(gameToken);
        Assert.assertThat(gameToken.length(), is(36));
        return gameToken;
    }

    private void testMakeTurn(final HttpHeaders headers) {
        final TurnDTO turnDTO = this.restTemplate.exchange(
                "http://localhost:" + port + "/game/makeTurn",
                HttpMethod.POST,
                new HttpEntity<>(
                        new UserChoiceDTO(Choice.P),
                        headers
                ),
                TurnDTO.class
        ).getBody();

        Assert.assertNotNull(turnDTO);
        Assert.assertNotNull(turnDTO.getOutcome());
        final StatsDTO stats = turnDTO.getStats();
        Assert.assertNotNull(stats);
        Assert.assertThat(
                stats.getDraws() + stats.getLoses() + stats.getWins(),
                is(1)
        );
    }

    private void testGetStats(final HttpHeaders headers) {
        final PlayerDTO playerDTO = this.restTemplate.exchange(
                "http://localhost:" + port + "/game/stats",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                PlayerDTO.class
        ).getBody();

        Assert.assertNotNull(playerDTO);
        final StatsDTO stats = playerDTO.getStats();

        Assert.assertThat(
                stats.getDraws() + stats.getWins() + stats.getLoses(),
                is(1)
        );
    }

    private void testDiscardGame(final HttpHeaders headers) {
        final ResponseEntity<Void> discardResponse = this.restTemplate.exchange(
                "http://localhost:" + port + "/game/discard",
                HttpMethod.DELETE,
                new HttpEntity<>(headers),
                Void.class
        );

        Assert.assertThat(
                discardResponse.getStatusCodeValue(),
                is(HttpStatus.OK.value())
        );
    }

}
