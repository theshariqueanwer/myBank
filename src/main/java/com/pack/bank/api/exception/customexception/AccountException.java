package com.pack.bank.api.exception.customexception;

public class AccountException extends RuntimeException {

    public AccountException(String message) {
        super(message);
    }
}
