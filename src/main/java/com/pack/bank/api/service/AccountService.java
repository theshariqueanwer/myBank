package com.pack.bank.api.service;

import com.pack.bank.api.customresponse.DeleteAccountResponse;
import com.pack.bank.api.dto.AccountDto;
import com.pack.bank.api.entity.Account;
import com.pack.bank.api.record.AccountDtoRecord;

import java.util.List;

public interface AccountService {

    public AccountDtoRecord openAccount(AccountDtoRecord accountDtoRecord);

    public AccountDto createAccount(AccountDto accountDto);

    public AccountDto getAccount(Long id);

    public List<AccountDto> getAccounts();

    public AccountDto updateAccount(Long id, AccountDto accountDto);

    public DeleteAccountResponse deleteAccount(Long id);

    // ---------------------------------------------------------------------------------------------------

    public AccountDto getAccountById(Long id);

    public List<AccountDto> getAllAccount();

    public List<AccountDto> getAllAccounts();

    public AccountDto deposit(Long id, double amount);

    public AccountDto withdrawal(Long id, double amount);

    public AccountDto depositAmountByAccountNumber(Long accountNumber, Double amount);

    public AccountDto withdrawalAmountByAccountNumber(Long accountNumber, Double amount);

    public AccountDto transactionByAccountNumber(Long accountNumber, String actionType, Double amount);

    public AccountDto doTransactionByAccountNumber(Long accountNumber, String actionType, Double amount);

    public AccountDto doTheTransactionByAccountNumber(Long accountNumber, String actionType, Double amount);

    public AccountDto makeTransactionByAccountNumber(Long accountNumber, String actionType, Double amount);

    public AccountDto makeTheTransactionByAccountNumber(Long accountNumber, String actionType, Double amount);


}