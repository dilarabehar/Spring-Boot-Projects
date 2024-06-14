package com.javaguides.Banking.App.service;

import com.javaguides.Banking.App.dto.AccountDto;
import com.javaguides.Banking.App.dto.TransferFunDto;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById (Long id);

    AccountDto deposit (Long id, double amount);

    AccountDto withdraw(Long id, double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long id);

    void transferFunds(TransferFunDto transferFunDto);

}
