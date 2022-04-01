package com.company.accountservices;

import com.company.Service;
import com.company.accounts.Account;
import com.company.accounts.CurrentAccount;
import com.company.accounts.SavingsAccount;
import com.company.persons.Person;
import com.company.personservices.PersonService;
import com.company.transaction.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CurrentAccountService implements CurrentAccountServiceInterface{

    private static CurrentAccountService instance;
    ArrayList<CurrentAccount> currentAccountList;

    private CurrentAccountService(){ currentAccountList = new ArrayList<>();}

    public static CurrentAccountService getInstance(){
        if (instance == null)
            instance = new CurrentAccountService();
        return instance;
    }

    public ArrayList<CurrentAccount> getCurrentAccountList() {
        return currentAccountList;
    }

    public void showCurrentAcountList(){
        int index = 1;
        for(CurrentAccount current : CurrentAccountService.getInstance().getCurrentAccountList())
            System.out.println(index++ + ". " + current);
    }

    // Interface Methods:

    @Override
    public CurrentAccount readCurrentAccount(){

        Account a = AccountService.getInstance().readAccount();    // We don't need to create a new object, we can reference the returned one

        float commission;
        boolean badInput = false;
        System.out.println("Commission Rate: ");

        commission = Service.getInstance().getSc().nextFloat();
        Service.getInstance().getSc().nextLine(); // clear the buffer


        if(commission < 0 || commission > 100)
            badInput = true;

        while(badInput){

            System.out.println("The commission rate is a percentage, hense it must be between 0 and 100%. Please type another commission rate.");
            System.out.println("Commission Rate: ");

            commission = Service.getInstance().getSc().nextFloat();
            Service.getInstance().getSc().nextLine(); // clear the buffer

            if(commission >= 0 && commission <= 100)
                badInput = false;
        }

        CurrentAccount ob = new CurrentAccount(a, commission);

        return ob;
    }


    @Override
    public void addCurrentAccount(){
        CurrentAccount ob = readCurrentAccount();

        AccountService.getInstance().getAccountHashMap().put(ob.getIBAN(), ob);

        getCurrentAccountList().add(ob);

    }

    @Override
    public List<Transaction> chooseTransactionHistory(){
        System.out.println("Choose the current account corresponding to the transaction history that ou would like to sort:");
        showCurrentAcountList();
        int choice = Service.getInstance().selectChoice(currentAccountList.size());

        CurrentAccount chosenAccount = currentAccountList.get(choice - 1);

        return chosenAccount.getTransactionHistory();
    }

    @Override
    public void sortTransactionHistory(){

        List<Transaction> chooseTransactionHistory = chooseTransactionHistory();

        Collections.sort(chooseTransactionHistory);

        for(Transaction t : chooseTransactionHistory)
            System.out.println(t);
    }
    @Override
    public void depositTransactions(){
        List<Transaction> chooseTransactionHistory = chooseTransactionHistory();

        for(Transaction t: chooseTransactionHistory)
            if(t.getAmount() >= 0)
                System.out.println(t);

    }


    @Override
    public void withdrawTransactions(){

        List<Transaction> chooseTransactionHistory = chooseTransactionHistory();

        for(Transaction t: chooseTransactionHistory)
            if(t.getAmount() < 0)
                System.out.println(t);
    }


}
