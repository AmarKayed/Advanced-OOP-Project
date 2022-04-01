package com.company.accountservices;

import com.company.accounts.Account;

public interface AccountServiceInterface {

    public void showAccounts();

    public Account readAccount();

    public void addAccount();

    public void deleteAccount();

    public void removeTemporaryObject();

}
