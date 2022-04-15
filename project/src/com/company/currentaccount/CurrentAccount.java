package com.company.currentaccount;

import com.company.account.Account;
import com.company.transaction.Transaction;
import java.util.*;

public class CurrentAccount extends Account {

    private float commission;
    private List<Transaction> transactionHistory;


    public CurrentAccount() {transactionHistory = new ArrayList<>();}


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
                super.toString() +
                ", commission=" + commission +
                ", transactionHistory=" + transactionHistory +
                '}';
    }

}

