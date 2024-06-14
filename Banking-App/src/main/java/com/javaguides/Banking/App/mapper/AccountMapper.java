package com.javaguides.Banking.App.mapper;

import com.javaguides.Banking.App.dto.AccountDto;
import com.javaguides.Banking.App.entity.Account;

public class AccountMapper {

    public static Account mapToAccunt (AccountDto accountDto){

        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );
        return account;
    }

    public static AccountDto mapToAccoutDto(Account account)
    {
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return accountDto;
    }
}
