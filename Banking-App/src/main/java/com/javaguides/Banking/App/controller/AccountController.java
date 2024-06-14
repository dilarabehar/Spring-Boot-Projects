package com.javaguides.Banking.App.controller;

import com.javaguides.Banking.App.dto.AccountDto;
import com.javaguides.Banking.App.dto.TransactionDto;
import com.javaguides.Banking.App.dto.TransferFunDto;
import com.javaguides.Banking.App.entity.Account;
import com.javaguides.Banking.App.service.AccountService;
import com.javaguides.Banking.App.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//SpringMVC restController Class
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;
    private TransactionService transactionService;

    public AccountController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    //Add Account REST API
    @PostMapping
    public ResponseEntity<AccountDto> addAccount (@RequestBody AccountDto accountDto)
    {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    //Get Account REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    //Deposit REST API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request){

        double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    //Withdraw REST API
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request){
        double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    //GetAllAccounts REST API

    @GetMapping("/get_all")
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accountDtoList = accountService.getAllAccounts();
        return ResponseEntity.ok(accountDtoList);
    }

    //DeleteAccountById REST API
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted succesfully.");
    }

    //Build transform REST API
    @PostMapping("/transfer")
    public ResponseEntity<String> transferFun(@RequestBody TransferFunDto transferFunDto){
        accountService.transferFunds(transferFunDto);
        return ResponseEntity.ok("Transfer Succesfull");
    }

    //Build Transaction REST API
    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TransactionDto>> fetchAccountTransactions(@PathVariable ("id") Long accountId){
        List<TransactionDto> transactions = transactionService.getAccountTransactions(accountId);
        return ResponseEntity.ok(transactions);
    }

}
