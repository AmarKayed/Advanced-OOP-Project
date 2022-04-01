package com.company.accountservices;

import com.company.accounts.CurrentAccount;
import com.company.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

public interface CurrentAccountServiceInterface {

    public CurrentAccount readCurrentAccount();

    public void addCurrentAccount();

    public List<Transaction> chooseTransactionHistory();

    public void sortTransactionHistory();


    public void depositTransactions();

    public void withdrawTransactions();
}
