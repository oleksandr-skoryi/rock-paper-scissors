package com.alexfaster.rps.service.game;

import com.alexfaster.rps.dto.ProfileDTO;
import com.alexfaster.rps.exception.SessionNotFoundException;
import com.alexfaster.rps.model.Profile;
import com.alexfaster.rps.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StatsService {

    private GameRepository gameRepository;

    public ProfileDTO getStats(final String token) {
        final Profile profile = gameRepository.findById(token)
                .orElseThrow(() -> new SessionNotFoundException(token));
        return new ProfileDTO(profile);
    }
}
