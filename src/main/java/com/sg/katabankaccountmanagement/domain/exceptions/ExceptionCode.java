package com.sg.katabankaccountmanagement.domain.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    ACCOUNT_NOT_FOUND(1, "Account not found");

    private final int code;
    private final String message;

}
