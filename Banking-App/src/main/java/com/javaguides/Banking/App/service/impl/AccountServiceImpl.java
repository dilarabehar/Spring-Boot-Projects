package com.javaguides.Banking.App.service.impl;

import com.javaguides.Banking.App.dto.AccountDto;
import com.javaguides.Banking.App.dto.TransferFunDto;
import com.javaguides.Banking.App.entity.Account;
import com.javaguides.Banking.App.entity.Transaction;
import com.javaguides.Banking.App.entity.TransactionType;
import com.javaguides.Banking.App.exception.AccountExcepiton;
import com.javaguides.Banking.App.exception.BalanceException;
import com.javaguides.Banking.App.exception.GlobalExceptionHandler;
import com.javaguides.Banking.App.mapper.AccountMapper;
import com.javaguides.Banking.App.repository.AccountRepository;
import com.javaguides.Banking.App.repository.TransactionRepository;
import com.javaguides.Banking.App.service.AccountService;
import com.javaguides.Banking.App.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;
    private static final String TRANSACTION_TYPE_DEPOSIT = "DEPOSIT";
    private static final String TRANSACTION_TYPE_WITHDRAW = "WITHDRAW";
    private static final String TRANSACTION_TYPE_TRANSFER = "TRANSFER";

    @Autowired
    private TransactionService transactionService;

    private TransactionType transactionType;


    //whenever spring will find the single cosntructor in a spring bean, then spring will automatically inject the dependency.
    public AccountServiceImpl(AccountRepository accountRepository,TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {

        Account account = AccountMapper.mapToAccunt(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccoutDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() ->new AccountExcepiton("Account does not exist."));
        return AccountMapper.mapToAccoutDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() ->new AccountExcepiton("Account does not exist."));
        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);

        transactionService.addTransaction(id,amount,TRANSACTION_TYPE_DEPOSIT);

        return AccountMapper.mapToAccoutDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(() ->new AccountExcepiton("Account does not exist."));

        if (account.getBalance() < amount)
        {
            throw new RuntimeException("Insufficient amount.");
        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);

        transactionService.addTransaction(id,amount,TRANSACTION_TYPE_WITHDRAW);

        return AccountMapper.mapToAccoutDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {

        List<Account> accountList= accountRepository.findAll();
        return accountList.stream()
                .map(account -> AccountMapper.mapToAccoutDto(account))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(()->new AccountExcepiton("Account does not exist."));
        accountRepository.deleteById(id);
    }

    @Override
    public void transferFunds(TransferFunDto transferFunDto) {
        //Retrieve the account from which we send the amount
        Account fromAccount = accountRepository
                .findById(transferFunDto.fromAccountId())
                .orElseThrow(()->new AccountExcepiton("Account does not exists"));

       Account toAccount =  accountRepository.findById(transferFunDto.toAccountId())
                .orElseThrow(()->new AccountExcepiton("Account does not exists"));
        if(fromAccount.getBalance()< transferFunDto.amount())
        {
            throw  new BalanceException("HAVE NOT ENOUGH MONEY");
        }else {
            fromAccount.setBalance(fromAccount.getBalance() - transferFunDto.amount());
            toAccount.setBalance(toAccount.getBalance() + transferFunDto.amount());
        }

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        transactionService.addTransaction(toAccount.getId(), transferFunDto.amount(),TRANSACTION_TYPE_TRANSFER);
        transactionService.addTransaction(fromAccount.getId(), transferFunDto.amount(),TRANSACTION_TYPE_TRANSFER);


    }


}
