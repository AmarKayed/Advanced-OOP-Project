package com.company.customer;

import com.company.Service;
import com.company.account.Account;
import com.company.account.AccountServiceImpl;
import com.company.address.Address;
import com.company.address.AddressServiceImpl;
import com.company.person.PersonServiceImpl;


import java.util.*;

public class CustomerServiceImpl implements CustomerService {

    private static CustomerServiceImpl instance;

    private ArrayList<Customer> customerList;

    private CustomerServiceImpl() {customerList = new ArrayList<Customer>();}

    public static CustomerServiceImpl getInstance(){
        if(instance == null)
            instance = new CustomerServiceImpl();
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
        for(Customer customer : CustomerServiceImpl.getInstance().getCustomerList())
            System.out.println(index++ + ". " + customer);
    }


    @Override
    public Customer readCustomer() {
        Customer ob = new Customer();
        PersonServiceImpl.getInstance().readPerson(ob);

        Address a = AddressServiceImpl.getInstance().readAddress();
        ob.setAddress(a);

        String job;
        System.out.println("Job: ");
        job = Service.getInstance().getSc().nextLine();
        ob.setJob(job);

        return ob;
    }

    @Override
    public void addCustomer(){

        Customer ob = CustomerServiceImpl.getInstance().readCustomer();

        PersonServiceImpl.getInstance().getPersonList().add(ob);

        CustomerServiceImpl.getInstance().getCustomerList().add(ob);
    }

    public void updateCustomer(Customer toUpdate){
        AddressServiceImpl.getInstance().updateAdress(toUpdate.getAddress());

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

        HashMap<Integer, Account> map = AccountServiceImpl.getInstance().getAccountHashMap();
        Set<Integer> removeKeys = new HashSet<>();          // We cannot remove multiple keys unless we store them in a Set/Collection


        for(Map.Entry me : map.entrySet()){

                possibleID = ((Account) me.getValue()).getHolder().getId();
                if (ob.getId() == possibleID)
                    removeKeys.add((Integer) me.getKey());

            }

        map.keySet().removeAll(removeKeys);
        System.out.println();
        System.out.println("After deleting person " + ob.getFirstName() + " " + ob.getLastName() + ", we also closed all his open bank accounts.");
        System.out.println("Remaining Accounts:\n");
        AccountServiceImpl.getInstance().showAccounts();

        customerList.remove(index);

    }



}
