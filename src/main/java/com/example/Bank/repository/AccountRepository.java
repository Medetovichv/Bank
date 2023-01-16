package com.example.Bank.repository;

import com.example.Bank.module.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByEmail(String email);

}
