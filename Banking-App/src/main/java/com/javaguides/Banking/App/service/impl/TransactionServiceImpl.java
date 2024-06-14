package com.javaguides.Banking.App.service.impl;


import com.javaguides.Banking.App.dto.TransactionDto;
import com.javaguides.Banking.App.exception.BalanceException;
import com.javaguides.Banking.App.mapper.TransactionMapper;
import com.javaguides.Banking.App.repository.TransactionRepository;
import com.javaguides.Banking.App.service.TransactionService;
import com.javaguides.Banking.App.entity.TransactionType;
import com.javaguides.Banking.App.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.javaguides.Banking.App.mapper.TransactionMapper.mapToTransactionDto;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction addTransaction(Long accountId, double amount, String transactionType) throws BalanceException {

        Transaction transaction = new Transaction();
        transaction.setAccountId(accountId);
        transaction.setAmount(amount);
        transaction.setTransactionType(transactionType);
        transaction.setTimestamp(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }

    @Override
    public List<TransactionDto> getAccountTransactions(Long accountId) {

        List<Transaction> transactions = transactionRepository
                .findByAccountIdOrderByTimestampDesc(accountId);

        return transactions.stream()
                .map((transaction)-> convertEntityToDto(transaction))
                .collect(Collectors.toList());

          }

    private TransactionDto convertEntityToDto(Transaction transaction)
    {
        return new TransactionDto(
                transaction.getId(),
                transaction.getAccountId(),
                transaction.getAmount(),
                transaction.getTransactionType(),
                transaction.getTimestamp()
                );
    }


}
