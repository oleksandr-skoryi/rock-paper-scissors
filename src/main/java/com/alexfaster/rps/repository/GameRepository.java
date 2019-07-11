package com.alexfaster.rps.repository;

import com.alexfaster.rps.model.Profile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
@Slf4j
public class GameRepository {

    private ConcurrentMap<String, Profile> storage;

    public GameRepository() {
        this.storage = new ConcurrentHashMap<>();
    }

    public Optional<Profile> findById(final String id) {
        return Optional.ofNullable(storage.get(id));
    }

    public Profile save(final Profile profile) {
        final Profile createdProfile = storage.merge(
                profile.getToken(),
                profile,
                (oldValue, newValue) -> newValue
        );
        log.info("Map: "+ storage.toString());
        return createdProfile;
    }

    public void delete(final String id) {
        storage.remove(id);
    }
}
