package com.alexfaster.rps.service.ai;

import com.alexfaster.rps.config.SkynetConfiguration;
import com.alexfaster.rps.model.Choice;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PatternService {

    private final SkynetConfiguration skynetConfiguration;

    public Optional<Choice> doPatternChoice(final List<Choice> playerChoices) {

        if (playerChoices.size() < skynetConfiguration.getMinChunkSize()) {
            return Optional.empty();
        }
        final String patternString = playerChoices.stream()
                .map(Enum::toString)
                .collect(Collectors.joining());

        final int maxPatternSize = Math.min(
                skynetConfiguration.getMaxChunkSize(),
                playerChoices.size()
        );

        for (int i = maxPatternSize; i >= skynetConfiguration.getMinChunkSize(); i--) {
            final String lastChunk = getLastChunk(patternString, i);
            final int firstIndex = patternString.indexOf(lastChunk);
            final int lastIndex = patternString.lastIndexOf(lastChunk);
            if (firstIndex != lastIndex) {
                final Choice choice = Choice.valueOf(
                        String.valueOf(
                                patternString.charAt(firstIndex + i)
                        )
                );
                return Optional.of(
                        Choice.getEnemy(choice)
                );
            }
        }
        return Optional.empty();
    }

    private String getLastChunk(
            final String patternString,
            final int size
    ) {
        return patternString.substring(patternString.length() - size);
    }
}
