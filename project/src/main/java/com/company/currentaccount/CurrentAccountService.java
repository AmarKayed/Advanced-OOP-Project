package com.company.currentaccount;

import com.company.transaction.Transaction;

import java.util.List;

public interface CurrentAccountService {

    public CurrentAccount read();

    public boolean add();     // returns true if it was successful, false otherwise

    public List<Transaction> chooseTransactionHistory();

    public void sortTransactionHistory();

    public void depositTransactions();

    public void withdrawTransactions();
}
