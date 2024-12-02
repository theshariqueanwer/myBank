package com.pack.bank.api.serviceimplementation;

import com.pack.bank.api.customresponse.DeleteAccountResponse;
import com.pack.bank.api.dto.AccountDto;
import com.pack.bank.api.entity.Account;
import com.pack.bank.api.exception.customexception.AccountException;
import com.pack.bank.api.exception.customexception.AccountNotFoundException;
import com.pack.bank.api.exception.customexception.InsufficientFundsException;
import com.pack.bank.api.mapper.AccountMapper;
import com.pack.bank.api.mapper.AccountMapperRecord;
import com.pack.bank.api.record.AccountDtoRecord;
import com.pack.bank.api.repository.AccountRepository;
import com.pack.bank.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AccountServiceImplementation implements AccountService {

    @Autowired
    private AccountRepository accountRepository;


    public static long generateAccountNumber() {
        Random random = new Random();
        return 56000000000000L + (long) (random.nextDouble() * 100000000000L);
    }

    @Override
    public AccountDtoRecord openAccount(AccountDtoRecord accountDtoRecord) {
        // accountDtoRecord.accountNumber(generateAccountNumber());
        Account account = AccountMapperRecord.mapToAccount(accountDtoRecord);
        account.setAccountNumber(generateAccountNumber());
        Account savedAccount = accountRepository.save(account);
        return AccountMapperRecord.mapToAccountDtoRecord(savedAccount);
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        accountDto.setAccountNumber(generateAccountNumber());
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(
                        // () -> new RuntimeException("Account does not exist with id " + id + " Please try with correct id")
                        () -> new AccountException("Account does not exist with id " + id + " Please try with correct id")

                );
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public List<AccountDto> getAccounts() {
        List<Account> accountList = accountRepository.findAll();
        return accountList.stream().map(AccountMapper::mapToAccountDto).collect(Collectors.toList());
    }

    @Override
    public AccountDto updateAccount(Long id, AccountDto accountDto) {
        Account account = accountRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Account does not exist with id " + id + " Please try with correct id")
                );
        Account updateAccount = AccountMapper.mapToAccount(accountDto);
        account.setAccountHolderName(updateAccount.getAccountHolderName());
        Account updatedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(updatedAccount);
    }

    @Override
    public DeleteAccountResponse deleteAccount(Long id) {
        //        // Find the account
        //        Account account = accountRepository.findById(id)
        //                .orElseThrow(
        //                        () -> new RuntimeException("Account does not exist with id " + id + " Please try with correct id")
        //                );
        //
        //        // Delete the account
        //        accountRepository.delete(account);
        //
        //        // return "Account with id " + id + "has been deleted successful" + "\n" + account;
        //        // Return the custom response
        //        String message = "Account with Id " + id + " has been deleted successfully.";
        //        return new DeleteAccountResponse(message, account);

        // ---------------------------------------------------------------------------
        // boolean accountExists = logic to check if account exists
        boolean accountExists = accountRepository.existsById(id);
        if (!accountExists) {
            throw new AccountNotFoundException("Account does not exist with id " + id + ". Please try with the correct id.");
        }
        Account account = accountRepository.findById(id).orElseThrow();
        // Logic to delete the account
        accountRepository.deleteById(id); // Deletes the account if it exists
        String message = "Account with Id " + id + " has been deleted successfully.";
        return new DeleteAccountResponse(message, account);
    }

    // --------------------------------------------------------------------------------------------------

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException(("Accounts does not exists")));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public List<AccountDto> getAllAccount() {
        List<Account> accountList = accountRepository.findAll();
        // return accountList.stream().map((account) -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
        return accountList.stream().map(AccountMapper::mapToAccountDto).collect(Collectors.toList());

    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accountList = accountRepository.findAll();
        return AccountMapper.mapToAccountDtoList(accountList);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException(("Accounts does not exists")));
        // double total = account.getAccountBalance() + amount;
        // account.setAccountBalance(total);
        account.setAccountBalance(account.getAccountBalance() + amount);
        Account saveDepositedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveDepositedAccount);
    }

    @Override
    public AccountDto withdrawal(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException(("Accounts does not exists")));
        // double total = account.getAccountBalance() - amount;
        // account.setAccountBalance(total);
        account.setAccountBalance(account.getAccountBalance() + amount);
        Account saveDepositedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveDepositedAccount);
    }

    @Override
    public AccountDto depositAmountByAccountNumber(Long accountNumber, Double amount) {
        Account account = accountRepository
                .findByAccountNumber(accountNumber);
        account.setAccountBalance(account.getAccountBalance() + amount);
        Account saveDepositedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveDepositedAccount);
    }

    @Override
    public AccountDto withdrawalAmountByAccountNumber(Long accountNumber, Double amount) {
        Account account = accountRepository
                .findByAccountNumber(accountNumber);
        account.setAccountBalance(account.getAccountBalance() - amount);
        Account saveDepositedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveDepositedAccount);
    }

    @Override
    public AccountDto transactionByAccountNumber(Long accountNumber, String actionType, Double amount) {
        Account account = accountRepository
                .findByAccountNumber(accountNumber);
        if (actionType.equals("deposit")) {
            account.setAccountBalance(account.getAccountBalance() + amount);
        } else if (actionType.equals("withdrawal")) {
            account.setAccountBalance(account.getAccountBalance() - amount);
        }
        Account saveDepositedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveDepositedAccount);
    }

    @Override
    public AccountDto doTransactionByAccountNumber(Long accountNumber, String actionType, Double amount) {
        Account account = accountRepository
                .findByAccountNumber(accountNumber);

        if (actionType.equals("deposit")) {
            account.setAccountBalance(account.getAccountBalance() + amount);
        } else if (actionType.equals("withdrawal")) {
            account.setAccountBalance(account.getAccountBalance() - amount);
        }
        Account saveDepositedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveDepositedAccount);
    }

    @Override
    public AccountDto doTheTransactionByAccountNumber(Long accountNumber, String actionType, Double amount) {
        //        Account account = accountRepository.findByAccountNumber(accountNumber);
        //
        //        if (actionType.equals("deposit")) {
        //            account.setAccountBalance(account.getAccountBalance() + amount);
        //        } else if (actionType.equals("withdrawal")) {
        //            if (account.getAccountBalance() < amount) {
        //                throw new RuntimeException("insufficient amount in your account");
        //            }
        //            account.setAccountBalance(account.getAccountBalance() - amount);
        //        }
        //        Account saveDepositedAccount = accountRepository.save(account);
        //        return AccountMapper.mapToAccountDto(saveDepositedAccount);

        // Find the account or throw a custom exception
        Optional<Account> account = Optional.ofNullable(accountRepository
                .findAccountByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account with this account number " + accountNumber + " does not exist")));


        // Perform the transaction
        switch (actionType.toLowerCase()) {
            case "deposit":
                // account.setAccountBalance(account.getAccountBalance() + amount);
                account.orElseThrow().setAccountBalance(account.get().getAccountBalance() + amount);
                break;
            case "withdrawal":
                if (account.orElseThrow().getAccountBalance() < amount) {
                    throw new InsufficientFundsException("Insufficient balance for this withdrawal amount " + amount + " In your account with accountNumber " + accountNumber);
                }
                // account.setAccountBalance(account.getAccountBalance() - amount);
                account.orElseThrow().setAccountBalance(account.get().getAccountBalance() - amount);
                break;
            default:
                throw new IllegalArgumentException("Invalid actionType : " + actionType + " Please try with valid actionType");
        }

        // Save the updated account with amount and return the DTO
        Account savedAccount = accountRepository.save(account.orElseThrow());
        System.out.println(savedAccount);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto makeTransactionByAccountNumber(Long accountNumber, String actionType, Double amount) {
        return null;
    }

    @Override
    public AccountDto makeTheTransactionByAccountNumber(Long accountNumber, String actionType, Double amount) {
        return null;
    }
}