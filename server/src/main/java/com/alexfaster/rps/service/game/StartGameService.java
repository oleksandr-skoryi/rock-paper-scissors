package com.alexfaster.rps.service.game;

import com.alexfaster.rps.dto.AccountDTO;
import com.alexfaster.rps.model.Account;
import com.alexfaster.rps.model.Player;
import com.alexfaster.rps.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StartGameService {

    private final AccountRepository accountRepository;

    @Transactional
    public AccountDTO startGame() {
        final String generatedToken = generateToken();
        final Account account = new Account();
        final Player player = new Player();
        account.setToken(generatedToken);
        account.setPlayer(player);
        player.setAccount(account);
        accountRepository.save(account);
        return new AccountDTO(
                generatedToken
        );
    }

    private String generateToken() {
        boolean unique = false;
        String token;
        do {
            token = UUID.randomUUID()
                    .toString();
            final Optional<Account> byToken = accountRepository.findById(token);
            if (!byToken.isPresent()) {
                unique = true;
            }
        } while (!unique);
        return token;
    }

}
