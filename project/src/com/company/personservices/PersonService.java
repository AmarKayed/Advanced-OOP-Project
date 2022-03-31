package com.company.personservices;

import com.company.Service;
import com.company.persons.Customer;
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

    public void showPersonList(){
        int index = 1;
        for(Person person : PersonService.getInstance().getPersonList())
            System.out.println(index++ + ". " + person);
    }

    @Override
    public Person readPerson(){
        Person ob = new Person();
        boolean badInput = false;

        System.out.println("First Name: ");
        String firstName = Service.getInstance().getSc().nextLine();
        ob.setFirstName(firstName);

        System.out.println("Last Name: ");
        String lastName = Service.getInstance().getSc().nextLine();
        ob.setLastName(lastName);

        System.out.println("Gender: ");
        ob.setGender(Service.getInstance().getSc().next().charAt(0));   // Reading only the first char
        Service.getInstance().getSc().nextLine(); // clear the buffer

        if(ob.getGender() != 'M' && ob.getGender() != 'F')
            badInput = true;

        while(badInput){
            System.out.println("The gender you entered isn't valid. Type 'M' for male or 'F' for female.");
            System.out.println("Gender: ");

            ob.setGender(Service.getInstance().getSc().next().charAt(0));   // Reading only the first char
            Service.getInstance().getSc().nextLine(); // clear the buffer

            if(ob.getGender() == 'M' || ob.getGender() == 'F')
                badInput = false;
        }

        removeTemporaryObject();    // So that the ID's remain in normal logical order
        return ob;
    }

    @Override
    public void addPerson(){
        System.out.println("\tWhat type of person shall we add:(choose an option using the index number)");
        System.out.println("\t\t1. Customer");
        System.out.println("\t\t2. Employee");
        System.out.println("\tYour choice: ");
        int choice = Service.getInstance().selectChoice(2);
        switch (choice){
            case 1:
                System.out.println("We're adding a Customer");
                CustomerService.getInstance().addCustomer();
                break;
            case 2:
                System.out.println("We're adding an Employee");
                EmployeeService.getInstance().addEmployee();
                break;
        }
        System.out.println("Back to addPerson()");
//        Service.getInstance().getSc().nextLine(); // clearing the buffer
    }

    @Override
    public void removeTemporaryObject(){
        Person.setNrOfPersons(Person.getNrOfPersons()-1);
    }


    public void deletePerson(){
        System.out.println("Choose the person you would like to delete");
        showPersonList();

        List<Person> personList = getPersonList();
        int choice = Service.getInstance().selectChoice(personList.size());

        Person toDelete = personList.get(choice - 1);
        if (toDelete instanceof Customer)
            CustomerService.getInstance().deleteCustomer((Customer) toDelete);

        personList.remove(choice - 1);

    }


}