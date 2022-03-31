package com.company.personservices;

import com.company.Service;
import com.company.address.Address;
import com.company.address.AddressService;
import com.company.persons.Customer;
import com.company.persons.Employee;
import com.company.persons.Person;

public class CustomerService implements CustomerServiceInterface{

    private static CustomerService instance;

    private CustomerService(){}

    public static CustomerService getInstance(){
        if(instance == null)
            instance = new CustomerService();
        return instance;
    }

    @Override
    public Customer readCustomer(){
        Person p = PersonService.getInstance().readPerson();    // We don't need to create a new object, we can reference the returned one

        Address a = AddressService.getInstance().readAddress();

        String job;
        System.out.println("Job: ");
        job = Service.getInstance().getSc().nextLine();

        Customer ob = new Customer(p, a, job);
        return ob;
    }

    @Override
    public void addCustomer(){

        Customer ob = CustomerService.getInstance().readCustomer();

        PersonService.getInstance().getPersonList().add(ob);
    }
}
