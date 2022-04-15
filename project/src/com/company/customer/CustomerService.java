package com.company.customer;

public interface CustomerService {

    public Customer read();

    public void add();

    public void update(Customer toUpdate);

    public void delete(Customer toDelete);

}
