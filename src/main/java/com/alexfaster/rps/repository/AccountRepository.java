package com.alexfaster.rps.repository;

import com.alexfaster.rps.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {

}
