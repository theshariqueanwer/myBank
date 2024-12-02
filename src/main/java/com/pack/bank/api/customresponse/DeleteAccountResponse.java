package com.pack.bank.api.customresponse;

import com.pack.bank.api.entity.Account;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class DeleteAccountResponse {
    private String message;
    private Account deletedAccount;
}
