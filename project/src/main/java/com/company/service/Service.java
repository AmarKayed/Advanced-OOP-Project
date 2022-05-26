package com.company.service;

import com.company.account.AccountServiceImpl;
import com.company.audit.AuditServiceImpl;
import com.company.config.DatabaseConfiguration;
import com.company.currentaccount.CurrentAccountServiceImpl;
import com.company.customer.CustomerServiceImpl;
import com.company.employee.Employee;
import com.company.person.PersonServiceImpl;
import com.company.repository.CustomerRepository;
import com.company.repository.EmployeeRepository;
import com.company.repository.PersonRepository;
import com.company.repository.TransactionRepository;
import com.company.transaction.Transaction;
import com.company.transaction.TransactionServiceImpl;

import java.util.Scanner;

public class Service {

    private static Service instance;

    private String selectedOption;
    private boolean validOption;
    private final Scanner sc;

    private final PersonServiceImpl personService;
    private final AccountServiceImpl accountService;
    private final CustomerServiceImpl customerService;
    private final CurrentAccountServiceImpl currentAccountService;
    private final TransactionServiceImpl transactionService;
    private final AuditServiceImpl auditService;

    private Service(){
        // This is a singleton class
        sc = new Scanner(System.in);
        validOption = true;

        personService = PersonServiceImpl.getInstance();
        accountService = AccountServiceImpl.getInstance();
        customerService = CustomerServiceImpl.getInstance();
        currentAccountService = CurrentAccountServiceImpl.getInstance();
        transactionService = TransactionServiceImpl.getInstance();
        auditService = AuditServiceImpl.getInstance();

    }

    public static Service getInstance(){
        if(instance == null)
            instance = new Service();
        return instance;
    }


    // Getters and Setters:

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    public boolean getValidOption() {
        return validOption;
    }

    public void setValidOption(boolean validOption) {
        this.validOption = validOption;
    }

    // We won't be needing a setter for Scanner sc

    public Scanner getSc() {
        return sc;
    }


    // Methods:


    public void loadCsvData(){
        System.out.println("I'm not sure how to implement this.");
    }

    public void databaseInit(){
        PersonRepository personRepository = PersonRepository.getInstance();
        CustomerRepository customerRepository = CustomerRepository.getInstance();
        EmployeeRepository employeeRepository = EmployeeRepository.getInstance();
        TransactionRepository transactionRepository = TransactionRepository.getInstance();
        personRepository.createTable();

//        personRepository.addPerson();
//        personRepository.displayPerson();

        customerRepository.createTable();
        employeeRepository.createTable();
        transactionRepository.createTable();

        DatabaseConfiguration.closeDatabaseConnection();
    }


    public int selectChoice(int limit){
        String input;
        boolean badInput = true;

        while(badInput) {
            input = sc.nextLine();

            try{
                float choice = Float.parseFloat(input);
                if(choice > limit)
                    System.out.println("\tThe supplied index MUST be smaller than " + limit);
                else if(choice < 1)
                    System.out.println("\t The supplied index MUST be greater than 0");
                else if(choice - (int) choice != 0)
                    System.out.println("\tThe supplied index MUST be an integer from the interval [1, " + limit + "]");
                else
                    badInput = false;
                if(badInput)
                    System.out.println("\tPlease try again: ");


                else
                    return (int) choice;
            }
            catch (NumberFormatException e){
                System.out.println("The input MUST be an integer. Please try again: ");
            }
        }
        return 0;
    }

    public String yesOrNo(){
        System.out.print("Your answer: ");
        String answer = getInstance().getSc().nextLine();
        while(!answer.equals("yes") && !answer.equals("no")){
            System.out.println("The answer must be either \"yes\" or \"no\". Please type another answer.");
            System.out.print("Your answer: ");
            answer = Service.getInstance().getSc().nextLine();
        }
        return answer;
    }

    public void optionsMenu(boolean title){
        if(title)
            System.out.println("\t\t\tWelcome to the best Java project you'll ever see!\n");

        System.out.println("\t\tThe options of this application are the following:\n");
        System.out.println("\t\tA - for adding a new person(customer or employee)");
        System.out.println("\t\tL - for listing all the available persons");
        System.out.println("\t\tU - for updating a person's info");
        System.out.println("\t\tD - for deleting a person");
        System.out.println("\t\tO - for opening a new bank account(current or savings)");
        System.out.println("\t\tC - for listing all the bank's available customers");
        System.out.println("\t\tB - for listing all the open bank accounts");
        System.out.println("\t\tQ - for listing all the open current accounts");
        System.out.println("\t\tK - for closing an open bank account");
        System.out.println("\t\tT - for adding a new transaction");
        System.out.println("\t\tP - for printing all the deposit transactions of a current account");
        System.out.println("\t\tW - for printing all the withdraw transactions of a current account");
        System.out.println("\t\tS - for printing all the transactions of a current account sorted based on the transaction date");
        System.out.println("\t\tH - for printing all the available commands/options of the application");
        System.out.println("\t\tX - for quitting the application");
    }


    public void run(){
        System.out.println("\nYour Command:  ");
        setSelectedOption(getSc().nextLine());
//        getSc().nextLine(); // clear the buffer

        switch (selectedOption){
            case "A":
                personService.add();
                break;
            case "L":
                personService.show();
                break;
            case "U":
                personService.update();
                break;
            case "D":
                personService.delete();
                break;
            case "O":
                accountService.add();
                break;
            case "C":
                customerService.show();
                break;
            case "B":
                accountService.show();
                break;
            case "Q":
                currentAccountService.show();
                break;
            case "K":
                accountService.delete();
                break;
            case "T":
                transactionService.read();
                break;
            case "P":
                currentAccountService.depositTransactions();
                break;
            case "W":
                currentAccountService.withdrawTransactions();
                break;
            case "S":
                currentAccountService.sortTransactionHistory();
                break;
            case "H":
                optionsMenu(false);
                break;
            case "X":
                return;
            default:
                System.out.println("Invalid Command.");
                setValidOption(false);
                break;
        }
        if(validOption)
            auditService.log(selectedOption);
        setValidOption(true);
        run();
    }
}
