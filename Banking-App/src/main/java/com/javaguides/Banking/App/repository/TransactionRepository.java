package com.javaguides.Banking.App.repository;

import com.javaguides.Banking.App.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import com.javaguides.Banking.App.entity.Account;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountIdOrderByTimestampDesc(Long accountId);
}
