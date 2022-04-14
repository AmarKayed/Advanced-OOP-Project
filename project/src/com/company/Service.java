package com.company;

import com.company.accountservices.AccountService;
import com.company.accountservices.CurrentAccountService;
import com.company.personservices.CustomerService;
import com.company.personservices.PersonService;
import com.company.transaction.TransactionService;

import java.util.Scanner;

public class Service {

    private static Service instance;

    private String selectedOption;
    private Scanner sc;

    private Service(){
        // This is a singleton class
        sc = new Scanner(System.in);
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

    // We won't be needing a setter for Scanner sc

    public Scanner getSc() {
        return sc;
    }


    // Methods:

    public int selectChoice(int limit){
        float choice = sc.nextFloat();
        sc.nextLine(); // clear the buffer
        boolean badInput = true;
        while(badInput) {
            if(choice > limit)
                System.out.println("\tThe supplied index MUST be smaller than " + limit);
            else if(choice < 1)
                System.out.println("\t The supplied index MUST be greater than 0");
            else if(choice - (int) choice != 0)
                System.out.println("\tThe supplied index MUST be an integer from the interval [1, " + limit + "]");
            else
                badInput = false;
            if(badInput) {
                System.out.println("\tPlease try again: ");
                choice = sc.nextFloat();
                sc.nextLine(); // clear the buffer


            }
        }
        return (int) choice;
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
                PersonService.getInstance().addPerson();
                break;
            case "L":
                PersonService.getInstance().showPersonList();
                break;
            case "U":
                PersonService.getInstance().updatePerson();
                break;
            case "D":
                PersonService.getInstance().deletePerson();
                break;
            case "O":
                AccountService.getInstance().addAccount();
                break;
            case "C":
                CustomerService.getInstance().showCustomerList();
                break;
            case "B":
                AccountService.getInstance().showAccounts();
                break;
            case "Q":
                CurrentAccountService.getInstance().showCurrentAccountList();
                break;
            case "K":
                AccountService.getInstance().deleteAccount();
                break;
            case "T":
                TransactionService.getInstance().readTransaction();
                break;
            case "P":
                CurrentAccountService.getInstance().depositTransactions();
                break;
            case "W":
                CurrentAccountService.getInstance().withdrawTransactions();
                break;
            case "S":
                CurrentAccountService.getInstance().sortTransactionHistory();
                break;
            case "H":
                Service.getInstance().optionsMenu(false);
                break;
            case "X":
                return;
            default:
                System.out.println("Invalid Command.");
                break;
        }
        run();
    }
}
