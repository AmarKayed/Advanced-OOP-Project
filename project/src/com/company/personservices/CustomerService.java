package com.company.personservices;

import com.company.Service;
import com.company.address.Address;
import com.company.address.AddressService;
import com.company.persons.Customer;
import com.company.persons.Employee;
import com.company.persons.Person;

import java.util.ArrayList;
import java.util.List;

public class CustomerService implements CustomerServiceInterface{

    private static CustomerService instance;

    private ArrayList<Customer> customerList;

    private CustomerService() {customerList = new ArrayList<Customer>();}

    public static CustomerService getInstance(){
        if(instance == null)
            instance = new CustomerService();
        return instance;
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(ArrayList<Customer> customerList) {
        this.customerList = customerList;
    }


    public void showCustomerList(){
        int index = 1;
        for(Customer customer : CustomerService.getInstance().getCustomerList())
            System.out.println(index++ + ". " + customer);
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

        CustomerService.getInstance().getCustomerList().add(ob);
    }

    public void deleteCustomer(Customer ob){
        int index = customerList.indexOf(ob);

        if(index < 0)
            return;

        customerList.remove(index);

    }

}
