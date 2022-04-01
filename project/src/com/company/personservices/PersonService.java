package com.company.personservices;

import com.company.Service;
import com.company.accounts.CurrentAccount;
import com.company.persons.Customer;
import com.company.persons.Employee;
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
//        System.out.println("HEEEEEEEEEEEERRRRRRRRRRREEEEEEEE");
//        System.out.println("Gender: ");
//        ob.setGender(Service.getInstance().getSc().next().charAt(0));   // Reading only the first char
//        Service.getInstance().getSc().nextLine(); // clear the buffer
//
//        if(ob.getGender() != 'M' && ob.getGender() != 'F')
//            badInput = true;
//
//        while(badInput){
//            System.out.println("The gender you entered isn't valid. Type 'M' for male or 'F' for female.");
//            System.out.println("Gender: ");
//
//            ob.setGender(Service.getInstance().getSc().next().charAt(0));   // Reading only the first char
//            Service.getInstance().getSc().nextLine(); // clear the buffer
//
//            if(ob.getGender() == 'M' || ob.getGender() == 'F')
//                badInput = false;
//        }

        String gender;
        gender = Service.getInstance().getSc().nextLine();
        if (gender.length() == 0)
            badInput = true;
        else if(gender.charAt(0) != 'M' && gender.charAt(0) != 'F')
            badInput = true;

        while(badInput){
            System.out.println("The gender you entered isn't valid. Type 'M' for male or 'F' for female.");
            System.out.println("Gender: ");

            gender = Service.getInstance().getSc().nextLine();

            if(gender.length() > 0)
                if(gender.charAt(0) == 'M' || gender.charAt(0) == 'F')
                    badInput = false;
        }

        ob.setGender(gender.charAt(0));

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
    public void updatePerson(){
        System.out.println("Choose the person you would like to update: ");
        showPersonList();

        List<Person> personList = getPersonList();
        int choice = Service.getInstance().selectChoice(personList.size());
        Person toUpdate = personList.get(choice - 1);

        System.out.println("The fields that can be updated will be printed below.");
        System.out.println("If you choose to keep the current value of the field you may type \"_keep\".");

        System.out.println("First Name: " + toUpdate.getFirstName());
        System.out.println("First Name Update Value: ");
        String firstName;
        firstName = Service.getInstance().getSc().nextLine();
        if(!firstName.equals("_keep"))
            toUpdate.setFirstName(firstName);

        System.out.println("Last Name: " + toUpdate.getLastName());
        System.out.println("Last Name Update Value: ");
        String lastName;
        lastName = Service.getInstance().getSc().nextLine();
        if(!lastName.equals("_keep"))
            toUpdate.setLastName(lastName);


        System.out.println("Gender: " + toUpdate.getGender());
        System.out.println("Gender Update Value: ");
        String gender;
        gender = Service.getInstance().getSc().nextLine();

        boolean badInput = false;

        if(!gender.equals("_keep")){

            if (gender.length() == 0)
                badInput = true;
            else if(gender.charAt(0) != 'M' && gender.charAt(0) != 'F')
                badInput = true;

            while(badInput){
                System.out.println("The gender you entered isn't valid. Type 'M' for male or 'F' for female.");
                System.out.println("Gender Update Value: ");

                gender = Service.getInstance().getSc().nextLine();

                if(gender.length() > 0)
                    if(gender.charAt(0) == 'M' || gender.charAt(0) == 'F')
                        badInput = false;
            }
            toUpdate.setGender(gender.charAt(0));
        }

        // Verify if we need to update Customer or Employee as well:

        if (toUpdate instanceof Customer)
            System.out.println("Customer");
        else if(toUpdate instanceof Employee)
            System.out.println("Employee");

    }


    @Override
    public void deletePerson(){
        System.out.println("Choose the person you would like to delete: ");
        showPersonList();

        List<Person> personList = getPersonList();
        int choice = Service.getInstance().selectChoice(personList.size());

        Person toDelete = personList.get(choice - 1);
        if (toDelete instanceof Customer)
            CustomerService.getInstance().deleteCustomer((Customer) toDelete);

        personList.remove(choice - 1);

    }

    @Override
    public void removeTemporaryObject(){
        Person.setNrOfPersons(Person.getNrOfPersons()-1);
    }
}
