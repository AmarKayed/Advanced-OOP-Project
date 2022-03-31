package com.company.transaction;

import com.company.Service;
import com.company.accounts.CurrentAccount;
import com.company.accountservices.CurrentAccountService;

import java.util.ArrayList;

public class TransactionService implements TransactionServiceInterface{

    private static TransactionService instance;

    private TransactionService(){}

    public static TransactionService getInstance() {
        if (instance == null)
            instance = new TransactionService();
        return instance;
    }


    // Interface Methods:

    @Override
    public Transaction readTransaction(){

        System.out.println("Select the current account for which the transaction will be made: ");
        CurrentAccountService.getInstance().showCurrentAcountList();

        ArrayList<CurrentAccount> currentAccountList = CurrentAccountService.getInstance().getCurrentAccountList();
        int choice = Service.getInstance().selectChoice(currentAccountList.size());

        System.out.println("Transaction Date: ");
        String transactionDate = Service.getInstance().getSc().nextLine();

        System.out.println("Transaction Amount: ");
        float amount = Service.getInstance().getSc().nextFloat();

        Transaction ob = new Transaction(currentAccountList.get(choice - 1).getIBAN(), transactionDate, amount);

        // Now that we've created the transaction, we must include it in the transactionHistory of the selected currentAccount

        System.out.println(currentAccountList.get(choice - 1));

        currentAccountList.get(choice - 1).getTransactionHistory().add(ob);
        return ob;
    }

}
