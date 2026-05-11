package com.bank.management.model;

import java.util.ArrayList;

public class AccountSearchResult {
    AccountSearchStatus status;
    Account account;

    public AccountSearchResult(AccountSearchStatus status, Account account) {
        this.status = status;
        this.account = account;
    }

    public AccountSearchStatus getStatus() {
        return status;
    }

    public Account getAccount() {
        return account;
    }
}
