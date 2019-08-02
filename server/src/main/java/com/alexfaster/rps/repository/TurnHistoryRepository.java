package com.alexfaster.rps.repository;

import com.alexfaster.rps.model.Player;
import com.alexfaster.rps.model.TurnHistory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TurnHistoryRepository extends JpaRepository<TurnHistory, Long> {

    List<TurnHistory> findByPlayer(
            final Player player,
            final Pageable page
    );

    Long countByPlayer(final Player player);
}
