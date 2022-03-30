package com.company;

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

    public void optionsMenu(){
        System.out.println("\t\t\tWelcome to the best Java project you'll ever see!");
        System.out.println();
        System.out.println("\t\tThe options of this application are the following:\n");
        System.out.println("\t\tA - for adding a new person(customer or employee)");
        System.out.println("\t\tL - for listing all the available persons");
        System.out.println("\t\tD - for deleting a person");
        System.out.println("\t\tO - for opening a new bank account(current or savings)");
        System.out.println("\t\tH - for printing all the available commands/options of the application");
        System.out.println("\t\tX - for quitting the application");
        System.out.println("aaaa");
    }


    public void run(){
        System.out.println("\nYour Command:  ");
        setSelectedOption(getSc().nextLine());

        switch (selectedOption){
            case "A":
                System.out.println("\tWhat type of person shall we add:(choose an option using the index number)");
                System.out.println("\t\t1. Customer");
                System.out.println("\t\t2. Employee");
                System.out.println("\tYour choice: ");
                int choice = selectChoice(2);
                switch (choice){
                    case 1:
                        System.out.println("We're adding a Customer");
                        break;
                    case 2:
                        System.out.println("We're adding an Employee");
                        break;
                }
                System.out.println("Here");
                sc.nextLine(); // clearing the buffer
                break;
            case "L":
                System.out.println();
                break;
            case "D":
                System.out.println();
                break;
            case "O":
                System.out.println();
                break;
            case "H":
                System.out.println();
                break;
            case "X":
                return;
            default:
                System.out.println("Invalid Command.");
                break;
        }
        run();
    }

    public int selectChoice(int limit){
        float choice = sc.nextFloat();
        boolean badInput = true;
        while(badInput) {
            if(choice > limit)
                System.out.println("\tThe supplied index MUST be smaller than " + limit);
            else if(choice - (int) choice != 0)
                System.out.println("\tThe supplied index MUST be an integer from the interval [1, " + limit + "]");
            else
                badInput = false;
            if(badInput) {
                System.out.println("\tPlease try again: ");
                choice = sc.nextFloat();
            }
        }
        return (int) choice;
    }



}
