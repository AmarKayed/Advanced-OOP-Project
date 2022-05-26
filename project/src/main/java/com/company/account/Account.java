package com.company.account;

import com.company.customer.Customer;

import java.util.Objects;

public abstract class Account {

    private static int nrOfAccounts = 0;

    private final int iban;
    private String openDate;
    private Customer holder;
    float balance;


    public Account() {
        this.iban = ++Account.nrOfAccounts;
    }


    public static int getNrOfAccounts() {
        return nrOfAccounts;
    }

    public static void setNrOfAccounts(int nrOfAccounts) {
        Account.nrOfAccounts = nrOfAccounts;
    }

    public int getIban() {
        return iban;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public Customer getHolder() {
        return holder;
    }

    public void setHolder(Customer holder) {
        this.holder = holder;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return iban == account.iban && Float.compare(account.balance, balance) == 0 && Objects.equals(openDate, account.openDate) && Objects.equals(holder, account.holder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iban, openDate, holder, balance);
    }

    @Override
    public String toString() {
        return "Account{" +
                "IBAN=" + iban +
                ", openDate='" + openDate + '\'' +
                ", holder=" + holder +
                ", balance=" + balance +
                '}';
    }
}
