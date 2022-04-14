package com.company.personservices;

import com.company.Service;
import com.company.accounts.Account;
import com.company.accounts.CurrentAccount;
import com.company.accountservices.AccountService;
import com.company.address.Address;
import com.company.address.AddressService;
import com.company.persons.Customer;
import com.company.persons.Employee;
import com.company.persons.Person;

import java.util.*;

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
        if(getCustomerList().isEmpty()){
            System.out.println("The bank doesn't have any customers yet.");
            return;
        }
        int index = 1;
        for(Customer customer : CustomerService.getInstance().getCustomerList())
            System.out.println(index++ + ". " + customer);
    }

/*
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
*/

    @Override
    public Customer readCustomer() {
        Customer ob = new Customer();
        PersonService.getInstance().readPerson(ob);

        Address a = AddressService.getInstance().readAddress();
        ob.setAddress(a);

        String job;
        System.out.println("Job: ");
        job = Service.getInstance().getSc().nextLine();
        ob.setJob(job);

        return ob;
    }

    @Override
    public void addCustomer(){

        Customer ob = CustomerService.getInstance().readCustomer();

        PersonService.getInstance().getPersonList().add(ob);

        CustomerService.getInstance().getCustomerList().add(ob);
    }

    public void updateCustomer(Customer toUpdate){
        AddressService.getInstance().updateAdress(toUpdate.getAddress());

        System.out.println("Job: " + toUpdate.getJob());
        System.out.println("Job Update Value: ");
        String job;
        job = Service.getInstance().getSc().nextLine();

        if (!job.equals("_keep"))
            toUpdate.setJob(job);

    }



    public void deleteCustomer(Customer ob){
        int index = customerList.indexOf(ob);

        if(index < 0)
            return;

        // We first close all open accounts

        int possibleID = 0;
        int key = 0;

        HashMap<Integer, Account> map = AccountService.getInstance().getAccountHashMap();
        Set<Integer> removeKeys = new HashSet<>();          // We cannot remove multiple keys unless we store them in a Set/Collection


        for(Map.Entry me : map.entrySet()){

                possibleID = ((Account) me.getValue()).getHolder().getId();
                if (ob.getId() == possibleID) {

//                    map.remove(me.getKey());              // We CANNOT REMOVE KEYS INDIVIDUALLY IN A FOR LOOP
                    removeKeys.add((Integer) me.getKey());

                }
            }

        map.keySet().removeAll(removeKeys);
        System.out.println();
        System.out.println("After deleting person " + ob.getFirstName() + " " + ob.getLastName() + ", we also closed all his open bank accounts.");
        System.out.println("Remaining Accounts:\n");
        AccountService.getInstance().showAccounts();

//        AccountService.getInstance().closeAccount(index);

        customerList.remove(index);

    }



}
