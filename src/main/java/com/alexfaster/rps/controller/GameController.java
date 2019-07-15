package com.alexfaster.rps.controller;

import com.alexfaster.rps.dto.AccountDTO;
import com.alexfaster.rps.dto.PlayerDTO;
import com.alexfaster.rps.dto.TurnDTO;
import com.alexfaster.rps.dto.UserChoiceDTO;
import com.alexfaster.rps.service.game.DiscardGameService;
import com.alexfaster.rps.service.game.MakeTurnService;
import com.alexfaster.rps.service.game.StartGameService;
import com.alexfaster.rps.service.game.StatsService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Api(
        produces = MediaType.APPLICATION_JSON_VALUE
)
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(
        value = "/game",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class GameController {

    private StartGameService startGameService;
    private DiscardGameService discardGameService;
    private MakeTurnService makeTurnService;
    private StatsService statsService;

    @PostMapping("/start")
    public AccountDTO startGame() {
        return startGameService.startGame();
    }

    @PostMapping(
            value = "/makeTurn",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public TurnDTO makeTurn(

            @RequestHeader("Game-Token")
            @NotBlank
            final String token,

            @RequestBody
            @Valid
            final UserChoiceDTO userChoice
    ) {
        return makeTurnService.makeTurn(
                token,
                userChoice.getChoice()
        );
    }

    @DeleteMapping("/discard")
    public void discardGame(

            @RequestHeader("Game-Token")
            @NotBlank
            final String token

    ) {
        discardGameService.discardGame(token);
    }

    @GetMapping("/stats")
    public PlayerDTO getStats(

            @RequestHeader("Game-Token")
            @NotBlank
            final String token

    ) {
        return statsService.getStats(token);
    }

}
