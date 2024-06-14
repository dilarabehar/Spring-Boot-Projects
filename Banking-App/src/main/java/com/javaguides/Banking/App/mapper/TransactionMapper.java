package com.javaguides.Banking.App.mapper;

import com.javaguides.Banking.App.dto.TransactionDto;
import com.javaguides.Banking.App.entity.Transaction;

public class TransactionMapper {

    public static TransactionDto mapToTransactionDto(Transaction transaction)
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
