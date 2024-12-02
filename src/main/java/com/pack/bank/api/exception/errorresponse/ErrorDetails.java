package com.pack.bank.api.exception.errorresponse;

import java.time.LocalDateTime;

public record ErrorDetails(LocalDateTime timestamp,
                           String message,
                           String details,
                           String errorCode,
                           String httpStatus,
                           String path) {
}
