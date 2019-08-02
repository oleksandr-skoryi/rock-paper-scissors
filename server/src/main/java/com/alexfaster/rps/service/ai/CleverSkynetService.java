package com.alexfaster.rps.service.ai;

import com.alexfaster.rps.config.SkynetConfiguration;
import com.alexfaster.rps.model.Choice;
import com.alexfaster.rps.model.Player;
import com.alexfaster.rps.model.TurnHistory;
import com.alexfaster.rps.repository.TurnHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class CleverSkynetService implements AIBrainable {

    private final SkynetConfiguration skynetConfiguration;
    private final TurnHistoryRepository turnHistoryRepository;

    @Override
    public Choice makeTurn(final Player player) {
        final Pageable page = PageRequest.of(
                0,
                skynetConfiguration.getChainLength(),
                Sort.Direction.DESC,
                "createdAt"
        );
        final List<TurnHistory> lastTurnsChain = turnHistoryRepository.findByPlayer(
                player,
                page
        );

        final List<Choice> playerChoices = lastTurnsChain
                .stream()
                .map(TurnHistory::getPlayerChoice)
                .collect(Collectors.toList());
        if (playerChoices.isEmpty()) {
            return doRandomChoice();
        }
        Collections.reverse(playerChoices);
        return doCleverChoice(playerChoices);
    }

    private Choice doRandomChoice() {
        final Choice[] values = Choice.values();
        final int randomChoice = (int) (Math.random() * values.length);
        return values[randomChoice];
    }

    private Choice doCleverChoice(final List<Choice> playerChoices) {
        final int[][] transitionMatrix = buildTransitionMatrix(playerChoices);
        final Choice lastChoice = playerChoices.get(playerChoices.size() - 1);
        final int[] enemyRow = transitionMatrix[lastChoice.ordinal()];
        final Choice mostProbableTransition = findMostProbableTransition(enemyRow);
        return Choice.getEnemy(mostProbableTransition);
    }

    private int[][] buildTransitionMatrix(final List<Choice> playerChoices) {
        final int[][] matrix = new int[3][3];
        for (int i = 0; i < playerChoices.size() - 1; i++) {
            final Choice current = playerChoices.get(i);
            final Choice next = playerChoices.get(i + 1);
            matrix[current.ordinal()][next.ordinal()]++;
        }
        return matrix;
    }

    private Choice findMostProbableTransition(final int[] enemyRow) {
        final int maxValue = Arrays.stream(enemyRow)
                .max()
                .orElseThrow(() -> new IllegalArgumentException("Empty array is not expected"));

        final int[] maxIndexes = IntStream.range(0, enemyRow.length)
                .filter(index -> enemyRow[index] == maxValue)
                .toArray();

        final int randomIndex = (int) (Math.random() * maxIndexes.length);

        final int maxIndex = maxIndexes[randomIndex];

        return Choice.values()[maxIndex];
    }

}
