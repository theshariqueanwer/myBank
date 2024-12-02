package com.pack.bank.api.mapper;

import com.pack.bank.api.entity.Account;
import com.pack.bank.api.record.AccountDtoRecord;

import java.util.ArrayList;
import java.util.List;

public class AccountMapperRecord {

    public static Account mapToAccount(AccountDtoRecord accountDtoRecord) {
        return new Account(
                accountDtoRecord.id(),
                accountDtoRecord.accountHolderName(),
                accountDtoRecord.accountNumber(),
                accountDtoRecord.accountBalance()
        );
    }

    public static AccountDtoRecord mapToAccountDtoRecord(Account account) {
        return new AccountDtoRecord(
                account.getId(),
                account.getAccountHolderName(),
                account.getAccountNumber(),
                account.getAccountBalance()
        );
    }


    public static List<Account> mapToAccountList(List<AccountDtoRecord> accountDtoRecordList) {
        // Create a new list to hold Account objects
        List<Account> accountList = new ArrayList<>();

        // Iterate through the list of AccountDTOs
        for (AccountDtoRecord accountDtoRecord : accountDtoRecordList) {
            // Map AccountDto to Account
            Account account = new Account();
            account.setId(accountDtoRecord.id());
            account.setAccountHolderName(accountDtoRecord.accountHolderName());
            account.setAccountNumber(accountDtoRecord.accountNumber());
            account.setAccountBalance(accountDtoRecord.accountBalance());
            // Add more field mappings if needed

            // Add the mapped Account to the list
            accountList.add(account);
        }
        // Return the list of Account objects
        return accountList;
    }


    public static List<AccountDtoRecord> mapToAccountDtoRecordList(List<Account> accountList) {
        // Create a new list to hold AccountDto objects
        List<AccountDtoRecord> accountDtoRecordList = new ArrayList<>();

        // Iterate through the list of AccountDTOs
        for(Account account : accountList) {

            // This few below lines of code not supporting now need to do little work on it
            // Map to Account to AccountDto
            // AccountDtoRecord accountDtoRecord = new AccountDtoRecord();
            // accountDtoRecord.id(account.getId());
            // accountDtoRecord.accountHolderName();
            // accountDtoRecord.accountNumber();
            // accountDtoRecord.accountBalance();
            // Add more field mappings if needed

            // Map to Account to AccountDto
            AccountDtoRecord accountDtoRecord = mapToAccountDtoRecord(account);
            // Add more field mappings if needed

            // Add the mapped Account to the list
            accountDtoRecordList.add(accountDtoRecord);
        }
        // Return the list of Account objects
        return accountDtoRecordList;
    }
}
