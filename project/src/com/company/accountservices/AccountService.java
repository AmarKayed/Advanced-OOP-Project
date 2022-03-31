package com.company.accountservices;

import com.company.Service;
import com.company.accounts.Account;
import com.company.accounts.SavingsAccount;
import com.company.persons.Customer;
import com.company.persons.Person;
import com.company.personservices.CustomerService;
import com.company.personservices.EmployeeService;
import com.company.personservices.PersonService;

import java.util.ArrayList;
import java.util.HashMap;
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


    public void showAccounts(){
        int index = 1;
        for(Map.Entry me : AccountService.getInstance().getAccountHashMap().entrySet())
            System.out.println(index++ + ". Key: " + me.getKey() + "   Value: " + me.getValue());
    }


    // Interface Methods:

    @Override
    public Account readAccount(){
        Account ob = new Account();

        System.out.println("Open Date: ");
        String openDate = Service.getInstance().getSc().nextLine();
        ob.setOpenDate(openDate);

        System.out.println("Holder: (must be a customer)");
        System.out.println("\tAvailable Customers: (choose based on index number)");
        CustomerService.getInstance().showCustomerList();
//        ArrayList<Person> personList = (ArrayList<Person>) PersonService.getInstance().getPersonList();     // Downcasting

        ArrayList<Customer> customerList = CustomerService.getInstance().getCustomerList();
        int choice = Service.getInstance().selectChoice(customerList.size());

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

        removeTemporaryObject();    // So that the ID's remain in normal logical order

        return ob;

    }

    public void addAccount(){
        System.out.println("\tWhat type of bank account shall we open:(choose an option using the index number)");
        System.out.println("\t\t1. Current Account");
        System.out.println("\t\t2. Savings Account");
        System.out.println("\tYour choice: ");
        int choice = Service.getInstance().selectChoice(2);
        switch (choice){
            case 1:
                System.out.println("We're opening a Current Account");
//                CurrentAccountService.getInstance().addCurrentAccount();
//                CustomerService.getInstance().addCustomer();
                break;
            case 2:
                System.out.println("We're opening a Savings Account");
                SavingsAccountService.getInstance().addSavingsAccount();
//                EmployeeService.getInstance().addEmployee();
                break;
        }
        System.out.println("Back to addAccount()");
//        Service.getInstance().getSc().nextLine(); // clearing the buffer
    }


    @Override
    public void removeTemporaryObject(){
        Account.setNrOfAccounts(Account.getNrOfAccounts() - 1);
    }

}
