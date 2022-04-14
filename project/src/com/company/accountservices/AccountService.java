package com.company.accountservices;

import com.company.Service;
import com.company.accounts.Account;
import com.company.accounts.CurrentAccount;
import com.company.accounts.SavingsAccount;
import com.company.persons.Customer;
import com.company.persons.Person;
import com.company.personservices.CustomerService;
import com.company.personservices.EmployeeService;
import com.company.personservices.PersonService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountService implements AccountServiceInterface{

    private static AccountService instance;

    private HashMap<Integer, Account> accountHashMap;

    private AccountService() {accountHashMap = new HashMap<Integer, Account>();}

    public static AccountService getInstance(){
        if (instance == null)
            instance = new AccountService();
        return instance;
    }

    public HashMap<Integer, Account> getAccountHashMap() {
        return accountHashMap;
    }

    public void closeAccount(int key){
        HashMap<Integer, Account> accountHashMap = getAccountHashMap();
        accountHashMap.remove(key);
    }


    // Interface Methods:


    @Override
    public boolean showAccounts(){
        if(getAccountHashMap().isEmpty()){
            System.out.println("No account has been opened yet.");
            return false;
        }
        int index = 1;
        for(Map.Entry me : AccountService.getInstance().getAccountHashMap().entrySet())
            System.out.println(index++ + ". Key: " + me.getKey() + "   Value: " + me.getValue());
        return true;
    }

    @Override
    public Account readAccount(Account ob){
        boolean chooseCustomer = true;  // Variable which determines whether we should choose a holder or not for our account
        int choice = 1; // Initially, we consider the chosen holder as being the first one that was added
        ArrayList<Customer> customerList = CustomerService.getInstance().getCustomerList();

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
                CustomerService.getInstance().addCustomer();
                System.out.println("The customer has been added successfully! Back to opening the bank account: ");
            }
        }


        System.out.println("Open Date: ");
        String openDate = Service.getInstance().getSc().nextLine();
        ob.setOpenDate(openDate);

        if(chooseCustomer){
            System.out.println("Holder: (must be a customer)");
            System.out.println("\tAvailable Customers: (choose based on index number)");
            CustomerService.getInstance().showCustomerList();
            choice = Service.getInstance().selectChoice(customerList.size());
        }

        ob.setHolder(customerList.get(choice - 1));

        float balance;
        boolean badInput = false;

        System.out.println("Initial Balance: ");
        balance = Service.getInstance().getSc().nextFloat();
        Service.getInstance().getSc().nextLine(); // clear the buffer

        if(balance < 0)
            badInput = true;
        while (badInput){
            System.out.println("The balance cannot be negative. Please try again: ");
            System.out.println("Initial Balance: ");

            balance = Service.getInstance().getSc().nextFloat();
            Service.getInstance().getSc().nextLine(); // clear the buffer

            if(balance >= 0)
                badInput = false;
        }
        ob.setBalance(balance);

        return ob;

    }

    @Override
    public void addAccount(){
        System.out.println("\tWhat type of bank account shall we open:(choose an option using the index number)");
        System.out.println("\t\t1. Current Account");
        System.out.println("\t\t2. Savings Account");
        System.out.println("\tYour choice: ");
        int choice = Service.getInstance().selectChoice(2);
        switch (choice){
            case 1:
                System.out.println("We're opening a Current Account");
                CurrentAccountService.getInstance().addCurrentAccount();
                break;
            case 2:
                System.out.println("We're opening a Savings Account");
                SavingsAccountService.getInstance().addSavingsAccount();
                break;
        }
        System.out.println("Back to addAccount()");
//        Service.getInstance().getSc().nextLine(); // clearing the buffer
    }



    @Override
    public void deleteAccount(){
        if(getAccountHashMap().isEmpty()){
            System.out.println("No account has been opened yet and thus no account can be closed.");
            return;
        }
        System.out.println("Choose the account that you would like to close:");

        showAccounts();

        int choice = Service.getInstance().selectChoice(accountHashMap.size());

        // Find the key corresponding to the choice
        int index = 1;
        int key = 0;
        for(Map.Entry me : accountHashMap.entrySet())
            if (index++ == choice)
                key = (int) me.getKey();


        System.out.println();
        closeAccount(key);
    }

}
