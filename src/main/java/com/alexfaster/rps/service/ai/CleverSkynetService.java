package com.alexfaster.rps.service.ai;

import com.alexfaster.rps.model.Choice;
import com.alexfaster.rps.model.Player;
import com.alexfaster.rps.model.TurnHistory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CleverSkynetService implements AIBrainable {

    @Override
    public Choice makeTurn(final Player player) {
        final List<Choice> playerChoices = player.getTurnHistory()
                .stream()
                .sorted(Comparator.comparing(TurnHistory::getCreatedAt))
                .map(TurnHistory::getPlayerChoice)
                .collect(Collectors.toList());
        if (playerChoices.isEmpty()) {
            return doRandomChoice();
        }
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
        int maxIndex = 0;
        for (int i = 1; i < enemyRow.length; i++) {
            if (enemyRow[i] > enemyRow[maxIndex] ) {
                maxIndex = i;
            }
        }
        return Choice.values()[maxIndex];
    }

}
