package com.company.accountservices;

import com.company.accounts.Account;

public interface AccountServiceInterface {

    public boolean showAccounts();

    public Account readAccount(Account ob);

    public void addAccount();

    public void deleteAccount();

}
