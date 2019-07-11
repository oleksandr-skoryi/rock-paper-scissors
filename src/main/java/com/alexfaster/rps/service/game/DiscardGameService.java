package com.alexfaster.rps.service.game;

import com.alexfaster.rps.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DiscardGameService {

    private GameRepository gameRepository;

    public void discardGame(final String token) {
        gameRepository.delete(token);
    }

}
