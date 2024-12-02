package com.pack.bank.api.dto;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class AccountDto {

    private Long id;
    private String accountHolderName;
    private Long accountNumber;
    private Double accountBalance;

}