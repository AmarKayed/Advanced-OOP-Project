package com.company.transaction;

import java.util.Objects;

public class Transaction {

    private final int IBAN;
    private String transactionDate;
    private float amount;

    public Transaction(int IBAN, String transactionDate, float amount) {
        this.IBAN = IBAN;
        this.transactionDate = transactionDate;
        this.amount = amount;
    }

    public int getIBAN() {
        return IBAN;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return IBAN == that.IBAN && Float.compare(that.amount, amount) == 0 && Objects.equals(transactionDate, that.transactionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IBAN, transactionDate, amount);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "IBAN=" + IBAN +
                ", transactionDate='" + transactionDate + '\'' +
                ", amount=" + amount +
                '}';
    }
}
