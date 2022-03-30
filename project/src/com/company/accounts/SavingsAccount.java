package com.company.accounts;

import com.company.persons.Customer;

import java.util.Objects;

public class SavingsAccount extends Account{

    private float interest;
    private int compoundInterval;


    public SavingsAccount() {
    }

    public SavingsAccount(String openDate, Customer holder, float initialBalance) {
        super(openDate, holder, initialBalance);
    }

    public SavingsAccount(String openDate, Customer holder, float initialBalance, float interest, int compoundInterval) {
        super(openDate, holder, initialBalance);
        this.interest = interest;
        this.compoundInterval = compoundInterval;
    }

    public SavingsAccount(Account ob, float interest, int compoundInterval) {
        super(ob);
        this.interest = interest;
        this.compoundInterval = compoundInterval;
    }


    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public int getCompoundInterval() {
        return compoundInterval;
    }

    public void setCompoundInterval(int compoundInterval) {
        this.compoundInterval = compoundInterval;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SavingsAccount that = (SavingsAccount) o;
        return Float.compare(that.interest, interest) == 0 && compoundInterval == that.compoundInterval;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), interest, compoundInterval);
    }

    @Override
    public String toString() {
        return "SavingsAccount{" +
                "interest=" + interest +
                ", compoundInterval=" + compoundInterval +
                '}';
    }
}
