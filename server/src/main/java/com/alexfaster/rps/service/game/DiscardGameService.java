package com.alexfaster.rps.service.game;

import com.alexfaster.rps.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DiscardGameService {

    private final AccountRepository accountRepository;

    @Transactional
    public void discardGame(final String token) {
        accountRepository.deleteById(token);
    }

}
