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

        SavingsAccount ob = new SavingsAccount();

        if(AccountService.getInstance().readAccount(ob) == null)
            return null;

        float interest = 0;
        float compoundInterval = 0;
        boolean badInput = true;
        String input;

        System.out.println("Interest Rate: ");

        while(badInput){
            input = Service.getInstance().getSc().nextLine();
            try{
                interest = Float.parseFloat(input);
                if(interest < 0 || interest > 100)
                    System.out.println("The interest rate is a percentage, hence it must be between 0 and 100%. Please type another interest rate.");
                else badInput = false;

            }
            catch (NumberFormatException e) {
                System.out.println("The interest rate MUST be a real number. Please try again:");
            }
        }

        ob.setInterest(interest);


        System.out.println("Compound Interval: (in number of months)");
        badInput = true;
        while(badInput){
            input = Service.getInstance().getSc().nextLine();
            try{
                compoundInterval = Float.parseFloat(input);
                if(compoundInterval < 1 || compoundInterval - (int) compoundInterval != 0)
                    System.out.println("The compound interval represents a number of months, which cannot be negative or decimal. Please type another compound interval.");
                else badInput = false;
            }
            catch (NumberFormatException e) {
                System.out.println("The interest rate MUST an integer. Please try again:");
            }
        }

        ob.setCompoundInterval((int) compoundInterval);

        return ob;

    }

    @Override
    public void addSavingsAccount(){

        SavingsAccount ob = readSavingsAccount();

        if(ob != null)
            AccountService.getInstance().getAccountHashMap().put(ob.getIban(), ob);
    }
}
