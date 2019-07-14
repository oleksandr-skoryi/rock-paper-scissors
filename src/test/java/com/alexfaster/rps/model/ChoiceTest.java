package com.alexfaster.rps.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;

@RunWith(MockitoJUnitRunner.class)
public class ChoiceTest {

    @Test
    public void verifyThatPaperEnemyDefinedCorrectly() {
        final Choice enemy = Choice.getEnemy(Choice.P);
        Assert.assertThat(enemy, is(Choice.S));
    }

    @Test
    public void verifyThatRockEnemyDefinedCorrectly() {
        final Choice enemy = Choice.getEnemy(Choice.R);
        Assert.assertThat(enemy, is(Choice.P));
    }

    @Test
    public void verifyThatRockScissorsEnemyDefinedCorrectly() {
        final Choice enemy = Choice.getEnemy(Choice.S);
        Assert.assertThat(enemy, is(Choice.R));
    }

}
