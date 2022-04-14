package com.company.accountservices;

import com.company.Service;
import com.company.accounts.Account;
import com.company.accounts.SavingsAccount;

public class SavingsAccountService implements SavingsAccountServiceInterface{


    private static SavingsAccountService instance;

    private SavingsAccountService(){}

    public static SavingsAccountService getInstance() {
        if (instance == null)
            instance = new SavingsAccountService();
        return instance;
    }

    @Override
    public SavingsAccount readSavingsAccount(){

        Account a = AccountService.getInstance().readAccount();    // We don't need to create a new object, we can reference the returned one

        float interest;
        float compoundInterval;
        boolean badInput = false;

        System.out.println("Interest Rate: ");

        interest = Service.getInstance().getSc().nextFloat();
        Service.getInstance().getSc().nextLine(); // clear the buffer


        if(interest < 0 || interest > 100)
            badInput = true;
        while(badInput){

            System.out.println("The interest rate is a percentage, hense it must be between 0 and 100%. Please type another interest rate.");
            System.out.println("interest: ");

            interest = Service.getInstance().getSc().nextFloat();
            Service.getInstance().getSc().nextLine(); // clear the buffer

            if(interest >= 0 && interest <= 100)
                badInput = false;
        }

        System.out.println("Compound Interval: (in number of months)");
        compoundInterval = Service.getInstance().getSc().nextInt();
        Service.getInstance().getSc().nextLine(); // clear the buffer


        if(compoundInterval < 1 || compoundInterval - (int) compoundInterval != 0)
            badInput = true;

        while (badInput){
            System.out.println("The compound interval represents a number of months, which cannot be negative or decimal. Please type another compound interval.");
            System.out.println("Compound Interval: (in number of months)");
            compoundInterval = Service.getInstance().getSc().nextInt();
            Service.getInstance().getSc().nextLine(); // clear the buffer
            if(!(compoundInterval < 1 || compoundInterval - (int) compoundInterval != 0))
                badInput = false;
        }

        SavingsAccount ob = new SavingsAccount(a, interest, (int) compoundInterval);

        return ob;

    }

    @Override
    public void addSavingsAccount(){

        SavingsAccount ob = readSavingsAccount();

        AccountService.getInstance().getAccountHashMap().put(ob.getIban(), ob);
    }
}
