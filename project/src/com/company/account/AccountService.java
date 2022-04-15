package com.company.account;

import com.company.account.Account;

public interface AccountService {

    public boolean showAccounts();

    public Account readAccount(Account ob);

    public void addAccount();

    public void deleteAccount();

}
