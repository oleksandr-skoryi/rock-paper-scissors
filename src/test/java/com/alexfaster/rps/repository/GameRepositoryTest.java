package com.alexfaster.rps.repository;

import com.alexfaster.rps.model.Profile;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.core.Is.is;

@RunWith(MockitoJUnitRunner.class)
public class GameRepositoryTest {

    private static final String TOKEN = "123";

    @InjectMocks
    private GameRepository gameRepository;

    @Test
    public void verifyThatSaveWorksAsCreate() {
        final Profile p = new Profile();
        p.assignToken(TOKEN);
        gameRepository.save(p);

        final Optional<Profile> fetchedById = gameRepository.findById(TOKEN);
        Assert.assertTrue(fetchedById.isPresent());
    }

    @Test
    public void verifyThatSaveWorksAsUpdate() {
        final Profile p = new Profile();
        p.assignToken(TOKEN);
        gameRepository.save(p);

        final Optional<Profile> fetchedById = gameRepository.findById(TOKEN);
        Assert.assertTrue(fetchedById.isPresent());

        p.incrementWins();
        gameRepository.save(p);

        final Optional<Profile> afterUpdate = gameRepository.findById(TOKEN);
        Assert.assertTrue(afterUpdate.isPresent());
        Assert.assertThat(afterUpdate.get().getWins(), is(1));
    }

    @Test
    public void verifyThatDeleteWorks() {
        final Profile p = new Profile();
        p.assignToken(TOKEN);
        gameRepository.save(p);

        final Optional<Profile> fetchedById = gameRepository.findById(TOKEN);
        Assert.assertTrue(fetchedById.isPresent());

        gameRepository.delete(TOKEN);

        final Optional<Profile> afterUpdate = gameRepository.findById(TOKEN);
        Assert.assertFalse(afterUpdate.isPresent());
    }

}
