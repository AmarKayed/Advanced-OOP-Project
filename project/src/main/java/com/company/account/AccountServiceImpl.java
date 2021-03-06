package com.company.account;

import com.company.service.Service;
import com.company.currentaccount.CurrentAccount;
import com.company.currentaccount.CurrentAccountServiceImpl;
import com.company.savingsaccount.SavingsAccountServiceImpl;
import com.company.customer.Customer;
import com.company.customer.CustomerServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountServiceImpl implements AccountService {

    private static AccountServiceImpl instance;

    private Map<Integer, Account> accountHashMap;

    private AccountServiceImpl() {accountHashMap = new HashMap<Integer, Account>();}

    public static AccountServiceImpl getInstance(){
        if (instance == null)
            instance = new AccountServiceImpl();
        return instance;
    }

    public Map<Integer, Account> getAccountHashMap() {
        return accountHashMap;
    }

    public void closeAccount(int key){
        Map<Integer, Account> accountHashMap = getAccountHashMap();
        if(accountHashMap.get(key) instanceof CurrentAccount)
            CurrentAccountServiceImpl.getInstance().closeAccount((CurrentAccount) accountHashMap.get(key));
        accountHashMap.remove(key);
    }


    // Interface Methods:


    @Override
    public boolean show(){
        if(getAccountHashMap().isEmpty()){
            System.out.println("No account has been opened yet.");
            return false;
        }
        int index = 1;
        for(Map.Entry me : AccountServiceImpl.getInstance().getAccountHashMap().entrySet())
            System.out.println(index++ + ". Key: " + me.getKey() + "   Value: " + me.getValue());
        return true;
    }

    @Override
    public Account read(Account ob){
        boolean chooseCustomer = true;  // Variable which determines whether we should choose a holder or not for our account
        int choice = 1; // Initially, we consider the chosen holder as being the first one that was added
        List<Customer> customerList = CustomerServiceImpl.getInstance().getCustomerList();

        if(customerList.isEmpty()){

            System.out.println("There are no customers for which we can open a new bank account.");
            System.out.println("Would you like to first add a customer?");
            System.out.println("Type \"yes\" for adding a new customer or \"no\" for aborting the opening of a new bank account:");

            String answer = Service.getInstance().yesOrNo();

            if(answer.equals("no")){
                System.out.println("No bank account will be opened.");
                return null;
            }
            else if(answer.equals("yes")){
                chooseCustomer = false;
                System.out.println("We're adding a Customer");
                CustomerServiceImpl.getInstance().add();
                System.out.println("The customer has been added successfully! Back to opening the bank account: ");
            }
        }


        System.out.println("Open Date: ");
        String openDate = Service.getInstance().getSc().nextLine();
        ob.setOpenDate(openDate);

        if(chooseCustomer){
            System.out.println("Holder: (must be a customer)");
            System.out.println("\tAvailable Customers: (choose based on index number)");
            CustomerServiceImpl.getInstance().show();
            choice = Service.getInstance().selectChoice(customerList.size());
        }

        ob.setHolder(customerList.get(choice - 1));

        float balance = 0;
        boolean badInput = true;
        String input;
        System.out.println("Initial Balance: ");

        while(badInput){
            input = Service.getInstance().getSc().nextLine();
            try{
                balance = Float.parseFloat(input);
                if (balance < 0)
                    System.out.println("The balance cannot be negative. Please try again: ");
                else
                    badInput = false;
            }
            catch (NumberFormatException e){
                System.out.println("The balance MUST be a real number. Please try again:");
            }
        }

        ob.setBalance(balance);

        return ob;

    }

    @Override
    public void add(){
        System.out.println("\tWhat type of bank account shall we open:(choose an option using the index number)");
        System.out.println("\t\t1. Current Account");
        System.out.println("\t\t2. Savings Account");
        System.out.println("\tYour choice: ");
        int choice = Service.getInstance().selectChoice(2);
        switch (choice){
            case 1:
                System.out.println("We're opening a Current Account");
                CurrentAccountServiceImpl.getInstance().add();
                break;
            case 2:
                System.out.println("We're opening a Savings Account");
                SavingsAccountServiceImpl.getInstance().add();
                break;
        }
        System.out.println("Back to addAccount()");
//        Service.getInstance().getSc().nextLine(); // clearing the buffer
    }



    @Override
    public void delete(){
        if(getAccountHashMap().isEmpty()){
            System.out.println("No account has been opened yet and thus no account can be closed.");
            return;
        }
        System.out.println("Choose the account that you would like to close:");

        show();

        int choice = Service.getInstance().selectChoice(accountHashMap.size());

        // Find the key corresponding to the choice
        int index = 1;
        int key = 0;
        for(Map.Entry me : accountHashMap.entrySet())
            if (index++ == choice)
                key = (int) me.getKey();


        System.out.println();
        closeAccount(key);
        System.out.println("The bank account has been successfully closed!");
    }

}
