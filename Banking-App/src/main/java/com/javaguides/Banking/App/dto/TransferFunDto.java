package com.javaguides.Banking.App.dto;

public record TransferFunDto(Long fromAccountId, Long toAccountId, double amount) {
}
