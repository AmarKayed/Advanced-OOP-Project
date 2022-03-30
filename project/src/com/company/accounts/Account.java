package com.company.accounts;

import com.company.persons.Customer;

import java.util.Objects;

public class Account {

    private static int nrOfAccounts = 0;

    private final int IBAN;
    private String openDate;
    private Customer holder;
    float balance;


    public Account() {
        this.IBAN = ++Account.nrOfAccounts;
    }

    public Account(String openDate, Customer holder, float initialBalance) {
        this.IBAN = ++Account.nrOfAccounts;
        this.openDate = openDate;
        this.holder = holder;
        this.balance = initialBalance;
    }

    public Account(Account ob){
        this.IBAN = ++Account.nrOfAccounts;
        this.openDate = ob.getOpenDate();
        this.holder = ob.getHolder();
        this.balance = ob.getBalance();
    }


    public static int getNrOfAccounts() {
        return nrOfAccounts;
    }

    public static void setNrOfAccounts(int nrOfAccounts) {
        Account.nrOfAccounts = nrOfAccounts;
    }

    public int getIBAN() {
        return IBAN;
    }

//    public void setIBAN(int IBAN) {
//        this.IBAN = IBAN;
//    }

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
        return IBAN == account.IBAN && Float.compare(account.balance, balance) == 0 && Objects.equals(openDate, account.openDate) && Objects.equals(holder, account.holder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IBAN, openDate, holder, balance);
    }

    @Override
    public String toString() {
        return "Account{" +
                "IBAN=" + IBAN +
                ", openDate='" + openDate + '\'' +
                ", holder=" + holder +
                ", balance=" + balance +
                '}';
    }
}
