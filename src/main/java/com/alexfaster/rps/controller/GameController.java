package com.alexfaster.rps.controller;

import com.alexfaster.rps.dto.ProfileDTO;
import com.alexfaster.rps.dto.StartGameDTO;
import com.alexfaster.rps.dto.TurnDTO;
import com.alexfaster.rps.dto.UserChoiceDTO;
import com.alexfaster.rps.service.game.MakeTurnService;
import com.alexfaster.rps.service.game.StartGameService;
import com.alexfaster.rps.service.game.DiscardGameService;
import com.alexfaster.rps.service.game.StatsService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Api
@RestController
@AllArgsConstructor
@RequestMapping(value = "/game", consumes = MediaType.APPLICATION_JSON_VALUE)
public class GameController {

    private StartGameService startGameService;
    private DiscardGameService discardGameService;
    private MakeTurnService makeTurnService;
    private StatsService statsService;

    @PostMapping("/start")
    public StartGameDTO startGame() {
        return startGameService.startGame();
    }

    @PostMapping("/makeTurn")
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

    @PostMapping("/discard")
    public void discardGame(

            @RequestHeader("Game-Token")
            @NotBlank
            final String token

    ) {
        discardGameService.discardGame(token);
    }

    @GetMapping("/stats")
    public ProfileDTO getStats(

            @RequestHeader("Game-Token")
            @NotBlank
            final String token

    ) {
        return statsService.getStats(token);
    }

}
