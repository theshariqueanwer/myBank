package com.pack.bank.api.record;

public record AccountDtoRecord(Long id,
                               String accountHolderName,
                               Long accountNumber,
                               Double accountBalance) {
}
