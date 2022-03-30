package com.company;

import java.util.Scanner;

public class Service {

    private static Service instance;

    private String selectedOption;
    private Scanner sc;

    private Service(){
        // This is a singleton class
    }

    public static Service getInstance(){
        if(instance == null)
            instance = new Service();
        return instance;
    }


    // Getters and Setters:
    


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

    }



}
