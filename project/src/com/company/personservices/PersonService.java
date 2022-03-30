package com.company.personservices;

import com.company.Service;
import com.company.persons.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonService implements PersonServiceInterface{

    private static PersonService instance;

    private List<Person> personList;

    private PersonService() {
        personList = new ArrayList<Person>();
    }

    public static PersonService getInstance() {
        if(instance == null)
            instance = new PersonService();
        return instance;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void addPerson(){
        System.out.println("\tWhat type of person shall we add:(choose an option using the index number)");
        System.out.println("\t\t1. Customer");
        System.out.println("\t\t2. Employee");
        System.out.println("\tYour choice: ");
        int choice = Service.getInstance().selectChoice(2);
        switch (choice){
            case 1:
                System.out.println("We're adding a Customer");
                break;
            case 2:
                System.out.println("We're adding an Employee");
                EmployeeService.getInstance().addEmployee();
                break;
        }
        System.out.println("Here");
        Service.getInstance().getSc().nextLine(); // clearing the buffer
    }
}
