package com.alexfaster.rps.service.game;

import com.alexfaster.rps.dto.PlayerDTO;
import com.alexfaster.rps.exception.SessionNotFoundException;
import com.alexfaster.rps.model.Account;
import com.alexfaster.rps.model.Player;
import com.alexfaster.rps.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class StatsService {

    private final AccountRepository accountRepository;
    private final LogService logService;

    @Transactional
    public PlayerDTO getStats(final String token) {
        final Player player = accountRepository.findById(token)
                .map(Account::getPlayer)
                .orElseThrow(() -> new SessionNotFoundException(token));
        return new PlayerDTO(player);
    }
}
