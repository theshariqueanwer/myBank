package com.pack.bank.api.controller;


import com.pack.bank.api.customresponse.DeleteAccountResponse;
import com.pack.bank.api.dto.AccountDto;
import com.pack.bank.api.record.AccountDtoRecord;
import com.pack.bank.api.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // Open Account REST API
    @PostMapping("/openAccount")
    public ResponseEntity<AccountDtoRecord> openAccount(@RequestBody AccountDtoRecord accountDtoRecord) {
        return new ResponseEntity<AccountDtoRecord>(accountService.openAccount(accountDtoRecord), HttpStatus.CREATED);
    }

    // Add Account REST API
    @PostMapping("/createAccount")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    // Get Account REST API
    @GetMapping("/getAccount/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable("id") Long id) {
        AccountDto accountDto = accountService.getAccount(id);
        return ResponseEntity.ok(accountDto);
    }

    // Get Accounts REST API
    @GetMapping("getAccounts")
    public ResponseEntity<List<AccountDto>> getAccounts() {
        List<AccountDto> accountDtoList = accountService.getAccounts();
        return ResponseEntity.ok(accountDtoList);
    }

    // Update Account REST API
    @PutMapping("/updateAccount/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable("id") Long id,
                                                    @RequestBody AccountDto accountDto) {
        return new ResponseEntity<AccountDto>(accountService.updateAccount(id, accountDto), HttpStatus.OK);
    }

    // Delete Account REST API
    @DeleteMapping("/deleteAccount/{id}")
    public ResponseEntity<DeleteAccountResponse> deleteAccountById(@PathVariable("id") Long id) {
        DeleteAccountResponse deleteAccountResponse = accountService.deleteAccount(id);
        // return ResponseEntity.ok("Account with Id " + id + " has been deleted successfully.");
        return ResponseEntity.ok(deleteAccountResponse);
    }

    // ---------------------------------------------------------------------------------------------------

    // Get Account REST API
    @GetMapping("/getAccountById/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable("id") Long id) {
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    // Get All Accounts REST API With AccountMapper method mapToAccountDtoList & mapToAccountList
    @GetMapping("/getAllAccounts")
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> accountDtoList = accountService.getAllAccounts();
        return ResponseEntity.ok(accountDtoList);
    }

    // Get All Account REST API With Help of the Steam Map Lambda method with Method Reference
    @GetMapping("/getAllAccount")
    public ResponseEntity<List<AccountDto>> getAllAccount() {
        List<AccountDto> accountDtoList = accountService.getAllAccount();
        return ResponseEntity.ok(accountDtoList);
    }

    // Deposit Account REST API By Id
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable("id") Long id,
                                              @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        // AccountDto accountDto = accountService.deposit(id, request.get("amount"));
        AccountDto accountDto = accountService.deposit(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    // Withdrawal Account REST API By Id
    @PutMapping("/{id}/withdrawal")
    public ResponseEntity<AccountDto> withdrawal(@PathVariable("id") Long id,
                                                 @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdrawal(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    // Deposit Amount to Account REST API By Account Number
    @PutMapping("/depositAmount")
    public ResponseEntity<AccountDto> depositAmountByAccountNumber(@PathParam("accountNumber") Long accountNumber,
                                                                   @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.depositAmountByAccountNumber(accountNumber, amount);
        return ResponseEntity.ok(accountDto);
    }

    // Withdrawal Amount from Account REST API By Account Number
    @PutMapping("/withdrawalAmount")
    public ResponseEntity<AccountDto> withdrawalAmountByAccountNumber(@RequestParam("accountNumber") Long accountNumber,
                                                                      @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdrawalAmountByAccountNumber(accountNumber, amount);
        return ResponseEntity.ok(accountDto);
    }

    // Transaction REST API With ActionType and RequestParam and RequestBody
    @PutMapping("/transaction")
    public ResponseEntity<AccountDto> transactionByAccountNumber(@RequestParam("accountNumber") Long accountNumber,
                                                                 @RequestParam("actionType") String actionType,
                                                                 @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.transactionByAccountNumber(accountNumber, actionType, amount);
        return ResponseEntity.ok(accountDto);
    }

    // Do Transaction REST API With ActionType and RequestParam and RequestBody
    @PutMapping("/doTransaction")
    public ResponseEntity<AccountDto> doTransactionByAccountNumber(@RequestParam Long accountNumber,
                                                                   @RequestParam String actionType,
                                                                   @RequestBody Map<String, Double> requestBody) {
        Double amount = requestBody.get("amount");
        AccountDto accountDto = accountService.doTransactionByAccountNumber(accountNumber, actionType, amount);
        return ResponseEntity.ok(accountDto);
    }

    // Do Transaction REST API With help of ActionType and RequestParam
    @PutMapping("/doTheTransaction")
    public ResponseEntity<AccountDto> doTheTransactionByAccountNumber(@RequestParam Long accountNumber,
                                                                      @RequestParam String action,
                                                                      @RequestParam Double amount) {

        AccountDto accountDto = accountService.doTheTransactionByAccountNumber(accountNumber, action, amount);
        return ResponseEntity.ok(accountDto);
    }

    // Make Transaction REST API
    @PutMapping("/makeTransaction")
    public ResponseEntity<AccountDto> makeTransactionByAccountNumber(@RequestParam Long accountNumber,
                                                                     @RequestParam String actionType,
                                                                     @RequestBody Map<String, Double> requestBody,
                                                                     HttpServletRequest request) {
        AccountDto accountDto = new AccountDto();
        Double amount = requestBody.get("amount");
        System.out.println(amount);
        // Check if the URL ends with "/make"
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        System.out.println(requestURI.endsWith("/make"));
        if (requestURI.endsWith("/make")) {
            accountDto = accountService.makeTransactionByAccountNumber(accountNumber, actionType, amount);
        }
        return ResponseEntity.ok(accountDto);
    }

    // Make The Transaction REST API
    @PutMapping("/makeTheTransaction")
    public ResponseEntity<AccountDto> makeTheTransactionByAccountNumber(@RequestParam Long accountNumber,
                                                                        @RequestParam String actionType,
                                                                        @RequestBody Double amount,
                                                                        HttpServletRequest request) {
        AccountDto accountDto = new AccountDto();
        System.out.println(amount);
        // Check if the URL ends with "/make"
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        System.out.println(requestURI.endsWith("/make"));
        if (requestURI.endsWith("/make")) {
            accountDto = accountService.makeTheTransactionByAccountNumber(accountNumber, actionType, amount);
        }
        return ResponseEntity.ok(accountDto);
    }

}