package com.example.Bank.repository;

import com.example.Bank.module.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByDateTimeContaining(String date);
    List<Transaction> findByTransaction(Long accountId);
}
