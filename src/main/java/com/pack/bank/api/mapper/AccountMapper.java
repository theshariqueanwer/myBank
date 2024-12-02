package com.pack.bank.api.mapper;

import com.pack.bank.api.dto.AccountDto;
import com.pack.bank.api.entity.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto) {
        //        Account account = new Account(
        //                accountDto.getId(),
        //                accountDto.getAccountHolderName(),
        //                accountDto.getAccountNumber(),
        //                accountDto.getAccountBalance()
        //                );
        //        return account;
        return new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getAccountNumber(),
                accountDto.getAccountBalance()
        );
    }

    public static AccountDto mapToAccountDto(Account account) {
        return new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getAccountNumber(),
                account.getAccountBalance()
        );
    }

    // public static List<Account> mapToAccountList(List<AccountDto> accountDtoList) {
    // }
    public static List<Account> mapToAccountList(List<AccountDto> accountDtoList) {
        // Create a new list to hold Account objects
        List<Account> accountList = new ArrayList<>();

        // Iterate through the list of AccountDTOs
        for (AccountDto accountDto : accountDtoList) {
            // Map AccountDto to Account
            Account account = new Account();
            account.setId(accountDto.getId());
            account.setAccountHolderName(accountDto.getAccountHolderName());
            account.setAccountNumber(accountDto.getAccountNumber());
            account.setAccountBalance(accountDto.getAccountBalance());
            // Add more field mappings if needed

            // Add the mapped Account to the list
            accountList.add(account);
        }
        // Return the list of Account objects
        return accountList;
    }

    // public static List<AccountDto> mapToAccountDtoList(List<Account> accountList) {
    // }
    public static List<AccountDto> mapToAccountDtoList(List<Account> accountList) {
        // Create a new list to hold AccountDto objects
        List<AccountDto> accountDtoList = new ArrayList<>();

        // Iterate through the list of AccountDTOs
        for(Account account : accountList) {
            // Map to Account to AccountDto
            AccountDto accountDto = new AccountDto();
            accountDto.setId(account.getId());
            accountDto.setAccountHolderName(account.getAccountHolderName());
            accountDto.setAccountNumber(account.getAccountNumber());
            accountDto.setAccountBalance(account.getAccountBalance());
            // Add more field mappings if needed

            // Add the mapped Account to the list
            accountDtoList.add(accountDto);
        }
        // Return the list of Account objects
        return accountDtoList;
    }

}