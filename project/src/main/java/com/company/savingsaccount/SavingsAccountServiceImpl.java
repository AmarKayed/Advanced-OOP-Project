package com.company.savingsaccount;

import com.company.service.CsvServiceImpl;
import com.company.service.Service;
import com.company.account.AccountServiceImpl;

public class SavingsAccountServiceImpl implements SavingsAccountService {


    private static SavingsAccountServiceImpl instance;

    private SavingsAccountServiceImpl(){}

    public static SavingsAccountServiceImpl getInstance() {
        if (instance == null)
            instance = new SavingsAccountServiceImpl();
        return instance;
    }

    @Override
    public SavingsAccount read(){

        SavingsAccount ob = new SavingsAccount();

        if(AccountServiceImpl.getInstance().read(ob) == null)
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
    public void add(){

        SavingsAccount ob = read();

        if(ob != null) {
            AccountServiceImpl.getInstance().getAccountHashMap().put(ob.getIban(), ob);
            CsvServiceImpl<SavingsAccount> csv = new CsvServiceImpl<>();
            csv.write(ob);
        }
    }
}
