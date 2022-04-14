package com.company.accountservices;

import com.company.Service;
import com.company.accounts.Account;
import com.company.accounts.CurrentAccount;
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

    public boolean showCurrentAccountList(){
        if(getCurrentAccountList().isEmpty()){
            System.out.println("No current account has been opened yet.");
            return false;
        }
        int index = 1;
        for(CurrentAccount current : CurrentAccountService.getInstance().getCurrentAccountList())
            System.out.println(index++ + ". " + current);
        return true;
    }

    // Interface Methods:

    @Override
    public CurrentAccount readCurrentAccount(){

        CurrentAccount ob = new CurrentAccount();
        AccountService.getInstance().readAccount(ob);

        if(ob == null)
            return null;

        float commission;
        boolean badInput = false;
        System.out.println("Commission Rate: ");

        commission = Service.getInstance().getSc().nextFloat();
        Service.getInstance().getSc().nextLine(); // clear the buffer


        if(commission < 0 || commission > 100)
            badInput = true;

        while(badInput){

            System.out.println("The commission rate is a percentage, hence it must be between 0 and 100%. Please type another commission rate.");
            System.out.println("Commission Rate: ");

            commission = Service.getInstance().getSc().nextFloat();
            Service.getInstance().getSc().nextLine(); // clear the buffer

            if(commission >= 0 && commission <= 100)
                badInput = false;
        }

        ob.setCommission(commission);

        return ob;
    }


    @Override
    public boolean addCurrentAccount(){     // returns true if it was successful, false otherwise
        CurrentAccount ob = readCurrentAccount();

        if(ob != null){
            AccountService.getInstance().getAccountHashMap().put(ob.getIban(), ob);

            getCurrentAccountList().add(ob);

            return true;
        }
        else return false;

    }

    @Override
    public List<Transaction> chooseTransactionHistory(){
        if(getCurrentAccountList().isEmpty()){
            System.out.println("There are no current accounts open yet. Hence, there have been no transactions made.");
            return null;
        }
        System.out.println("Choose the current account corresponding to the transaction history that ou would like to sort:");
        showCurrentAccountList();
        int choice = Service.getInstance().selectChoice(currentAccountList.size());

        CurrentAccount chosenAccount = currentAccountList.get(choice - 1);

        return chosenAccount.getTransactionHistory();
    }

    @Override
    public void sortTransactionHistory(){

        List<Transaction> chosenTransactionHistory = chooseTransactionHistory();
        if(chosenTransactionHistory == null)
            return;

        Collections.sort(chosenTransactionHistory);

        for(Transaction t : chosenTransactionHistory)
            System.out.println(t);
    }
    @Override
    public void depositTransactions(){
        List<Transaction> chosenTransactionHistory = chooseTransactionHistory();

        if(chosenTransactionHistory == null)
            return;

        for(Transaction t: chosenTransactionHistory)
            if(t.getAmount() >= 0)
                System.out.println(t);

    }


    @Override
    public void withdrawTransactions(){

        List<Transaction> chosenTransactionHistory = chooseTransactionHistory();

        if(chosenTransactionHistory == null)
            return;

        for(Transaction t: chosenTransactionHistory)
            if(t.getAmount() < 0)
                System.out.println(t);
    }


}
