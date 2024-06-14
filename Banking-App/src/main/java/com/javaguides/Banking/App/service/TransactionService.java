package com.javaguides.Banking.App.service;

import com.javaguides.Banking.App.dto.TransactionDto;
import com.javaguides.Banking.App.entity.Transaction;
import com.javaguides.Banking.App.entity.TransactionType;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    public Transaction addTransaction(Long accountId, double amount, String transactionType);
    List<TransactionDto> getAccountTransactions(Long accountId);
}
