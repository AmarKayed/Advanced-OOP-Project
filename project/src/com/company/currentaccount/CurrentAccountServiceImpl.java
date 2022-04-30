package com.company.currentaccount;

import com.company.customer.Customer;
import com.company.service.CsvServiceImpl;
import com.company.service.Service;
import com.company.account.AccountServiceImpl;
import com.company.transaction.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CurrentAccountServiceImpl implements CurrentAccountService {

    private static CurrentAccountServiceImpl instance;
    ArrayList<CurrentAccount> currentAccountList;

    private CurrentAccountServiceImpl(){ currentAccountList = new ArrayList<>();}

    public static CurrentAccountServiceImpl getInstance(){
        if (instance == null)
            instance = new CurrentAccountServiceImpl();
        return instance;
    }

    public ArrayList<CurrentAccount> getCurrentAccountList() {
        return currentAccountList;
    }

    public boolean show(){
        if(getCurrentAccountList().isEmpty()){
            System.out.println("No current account has been opened yet.");
            return false;
        }
        int index = 1;
        for(CurrentAccount current : CurrentAccountServiceImpl.getInstance().getCurrentAccountList())
            System.out.println(index++ + ". " + current);
        return true;
    }

    public void closeAccount(CurrentAccount ob){
        ArrayList<CurrentAccount> currentAccountArrayList = getCurrentAccountList();
        currentAccountArrayList.remove(ob);
    }

    // Interface Methods:

    @Override
    public CurrentAccount read(){

        CurrentAccount ob = new CurrentAccount();

        if(AccountServiceImpl.getInstance().read(ob) == null)
            return null;

        float commission = 0;
        boolean badInput = true;
        String input;
        System.out.println("Commission Rate: ");

        while(badInput){
            input = Service.getInstance().getSc().nextLine();
            try{
                commission = Float.parseFloat(input);
                if(commission < 0 || commission > 100)
                    System.out.println("The commission rate is a percentage, hence it must be between 0 and 100%. Please type another commission rate.");
                else
                    badInput = false;
            }
            catch (NumberFormatException e){
                System.out.println("The commission MUST be a real number. Please try again:");
            }
        }

        ob.setCommission(commission);

        return ob;
    }


    @Override
    public boolean add(){     // returns true if it was successful, false otherwise
        CurrentAccount ob = read();

        if(ob != null){
            AccountServiceImpl.getInstance().getAccountHashMap().put(ob.getIban(), ob);

            getCurrentAccountList().add(ob);

            CsvServiceImpl<CurrentAccount> csv = new CsvServiceImpl<>();
            csv.write(ob);

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
        show();
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
