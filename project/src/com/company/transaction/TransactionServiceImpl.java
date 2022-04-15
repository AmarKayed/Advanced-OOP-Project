package com.company.transaction;

import com.company.Service;
import com.company.accounts.CurrentAccount;
import com.company.accountservices.CurrentAccountServiceImpl;

import java.util.ArrayList;

public class TransactionServiceImpl implements TransactionService {

    private static TransactionServiceImpl instance;

    private TransactionServiceImpl(){}

    public static TransactionServiceImpl getInstance() {
        if (instance == null)
            instance = new TransactionServiceImpl();
        return instance;
    }


    // Interface Methods:

    @Override
    public Transaction readTransaction(){
        boolean chooseAccount = true;  // Variable which determines whether we should choose an account or not for our transaction
        int choice = 1; // Initially, we consider the chosen account as being the first one that was opened
        ArrayList<CurrentAccount> currentAccountList = CurrentAccountServiceImpl.getInstance().getCurrentAccountList();

        if(CurrentAccountServiceImpl.getInstance().getCurrentAccountList().size() == 0) {

            System.out.println("In order to make a new transaction a current account must be opened.");
            System.out.println("No current accounts have been opened so far.");
            System.out.println("Would you like to open one?(type \"yes\" or \"no\")");

            String answer = Service.getInstance().yesOrNo();

            if(answer.equals("no")){
                System.out.println("No transactions will be added.");
                return null;
            }
            else if(answer.equals("yes")) {
                chooseAccount = false;
                System.out.println("We're opening a Current Account");
                if(CurrentAccountServiceImpl.getInstance().addCurrentAccount() == false){
                    System.out.println("No transactions will be added.");
                    return null;
                }

            }
        }

        if(chooseAccount){
            System.out.println("Select the current account for which the transaction will be made: ");
            CurrentAccountServiceImpl.getInstance().showCurrentAccountList();
            choice = Service.getInstance().selectChoice(currentAccountList.size());
        }
        else
            System.out.println("The account was opened successfully! Back to adding the transaction:");

        System.out.println("Transaction Date: ");
        String transactionDate = Service.getInstance().getSc().nextLine();

        System.out.println("Transaction Amount: ");
        float amount = 0;
        String input;
        boolean badInput = true;
        while(badInput){
            input = Service.getInstance().getSc().nextLine();
            try{
                amount = Float.parseFloat(input);
                badInput = false;   // if no exceptions were thrown, then the input is good
            }
            catch (NumberFormatException e){
                System.out.println("The transaction amount MUST be a real number. Please try again:");
            }
        }

        Transaction ob = new Transaction(currentAccountList.get(choice - 1).getIban(), transactionDate, amount);

        // Now that we've created the transaction, we must include it in the transactionHistory of the selected currentAccount

        currentAccountList.get(choice - 1).getTransactionHistory().add(ob);

        System.out.println(currentAccountList.get(choice - 1));

        return ob;
    }

}
