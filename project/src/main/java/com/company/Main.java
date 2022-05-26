package com.company;

import com.company.service.Service;

public class Main {

    public static void main(String[] args) {

        Service.getInstance().databaseInit();
        Service.getInstance().optionsMenu(true);    // We first print the "Welcome" menu
        Service.getInstance().run();
        System.out.println("Back to Main.");

    }
}