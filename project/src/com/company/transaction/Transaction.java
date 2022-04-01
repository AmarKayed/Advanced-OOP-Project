package com.company.transaction;

import java.util.*;

public class Transaction implements Comparable{

    private final int IBAN;
    private String transactionDate;
    private float amount;

    private static List<String> monthList = new ArrayList<String>(Arrays.asList("JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV","DEC" ));


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

    @Override
    public int compareTo(Object o) {
        String[] t1 = this.transactionDate.split("\\s+");
        String[] t2 = ((Transaction) o).getTransactionDate().split("\\s+");
        int year1, year2;
        String month1, month2;
        int day1, day2;
        try {
            year1 = Integer.parseInt(t1[2]);
            year2 = Integer.parseInt(t2[2]);
            if(year1 < year2)   // We're comparing the years
                return -1;  // If the first date has a year smaller than the second date, then the first date is smaller/more recent
            else if (year1 == year2){
                month1 = t1[1];
                month2 = t2[1];
                if(monthList.indexOf(month1) != -1 && monthList.indexOf(month2) != -1) {
                    if (monthList.indexOf(month1) < monthList.indexOf(month2))
                        return -1;
                    else if(monthList.indexOf(month1) == monthList.indexOf(month2)){
                        day1 = Integer.parseInt(t1[0]);
                        day2 = Integer.parseInt(t2[0]);
                        if(day1 < day2)
                            return -1;
                        else if(day1 == day2)
                            return 0;
                        else return 1;
                    }
                    else
                        return 1;
                }
                else{
                    System.out.println("One of the dates has an invalid month.");
                }
            }
            else
                return 1;   // The first date is bigger than the second one

        }
        catch(java.lang.NumberFormatException e) {
            System.out.println("One of the dates is wrong, the sort isn't correct.");
        }
        catch (java.lang.StringIndexOutOfBoundsException e){
            System.out.println("One of the dates doesn't have all the necessary fields(day, month, year).");
        }


        return 0;
    }
}
