package com.alexfaster.rps.service.game;

import com.alexfaster.rps.dto.StartGameDTO;
import com.alexfaster.rps.model.Profile;
import com.alexfaster.rps.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class StartGameService {

    private GameRepository gameRepository;

    public StartGameDTO startGame() {
        final String generatedToken = generateToken();
        final Profile profile = createProfile(generatedToken);
        gameRepository.save(profile);
        return new StartGameDTO(
                generatedToken
        );
    }

    private String generateToken() {
        return UUID.randomUUID()
                .toString();
    }

    private Profile createProfile(final String generatedToken) {
        final Profile profile = new Profile();
        profile.assignToken(generatedToken);
        return profile;
    }

}
