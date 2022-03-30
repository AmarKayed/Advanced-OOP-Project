package com.company.accounts;

import com.company.Transaction;
import com.company.persons.Customer;
import com.company.persons.Employee;

import java.util.*;

public class CurrentAccount extends Account{

    private float commission;
    private List<Transaction> transactionHistory;


    public CurrentAccount() {}

    public CurrentAccount(String openDate, Customer holder, float initialBalance) {
        super(openDate, holder, initialBalance);
    }

    public CurrentAccount(String openDate, Customer holder, float initialBalance, float commission, List<Transaction> transactionHistory) {
        super(openDate, holder, initialBalance);
        this.commission = commission;
        this.transactionHistory = transactionHistory;
    }

    public CurrentAccount(Account ob, float commission, List<Transaction> transactionHistory) {
        super(ob);
        this.commission = commission;
        this.transactionHistory = transactionHistory;
    }


    public float getCommission() {
        return commission;
    }

    public void setCommission(float commission) {
        this.commission = commission;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<Transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CurrentAccount that = (CurrentAccount) o;
        return Float.compare(that.commission, commission) == 0 && Objects.equals(transactionHistory, that.transactionHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), commission, transactionHistory);
    }

    @Override
    public String toString() {
        return "CurrentAccount{" +
                "commission=" + commission +
                ", transactionHistory=" + transactionHistory +
                '}';
    }

}

