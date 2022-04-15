package com.company.accounts;

import java.util.Objects;

public class SavingsAccount extends Account{

    private float interest;
    private int compoundInterval;


    public SavingsAccount() {
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
                super.toString() +
                ", interest=" + interest +
                ", compoundInterval=" + compoundInterval +
                '}';
    }
}
