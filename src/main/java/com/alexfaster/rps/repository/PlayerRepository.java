package com.alexfaster.rps.repository;

import com.alexfaster.rps.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
