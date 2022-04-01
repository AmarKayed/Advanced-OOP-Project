package com.company.personservices;

import com.company.persons.Customer;

public interface CustomerServiceInterface {

    public Customer readCustomer();

    public void addCustomer();

    public void updateCustomer(Customer toUpdate);

    public void deleteCustomer(Customer toDelete);

}
