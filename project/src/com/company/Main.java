package com.company;

import com.company.audit.AuditServiceImpl;
import com.company.currentaccount.CurrentAccount;
import com.company.currentaccount.CurrentAccountServiceImpl;
import com.company.customer.Customer;
import com.company.customer.CustomerServiceImpl;
import com.company.service.CsvServiceImpl;
import com.company.service.Service;

public class Main {


    public static void main(String[] args) {

        Service.getInstance().optionsMenu(true);    // We first print the "Welcome" menu
        Service.getInstance().run();
        System.out.println("Back to Main.");

//        CsvServiceImpl<CurrentAccount> csv = new CsvServiceImpl<>();
//        CurrentAccount ob = CurrentAccountServiceImpl.getInstance().read();
//        System.out.println(ob);
//        csv.convertToCsvFormat(ob);
//        csv.write(ob);
    }


}

        /*
yes
Robert
Downey
M
USA
Florida
Actor
31 AUG 2020
1
1000
10

         */