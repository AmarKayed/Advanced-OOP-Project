package com.company.persons;

import com.company.address.Address;

import java.util.Objects;

public class Customer extends Person {

    private Address address;
    private String job;


    public Customer() {
        super();
    }

    public Customer(String firstName, String lastName, char gender) {
        super(firstName, lastName, gender);
    }

    public Customer(String firstName, String lastName, char gender, Address address, String job) {
        super(firstName, lastName, gender);
        this.address = address;
        this.job = job;
    }

    public Customer(Person ob, Address address, String job){
        super(ob);
        this.address = address;
        this.job = job;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(address, customer.address) && Objects.equals(job, customer.job);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), address, job);
    }

    @Override
    public String toString() {
        return "Customer{" +
                super.toString() +
                ", address=" + address +
                ", job='" + job + '\'' +
                '}';
    }

}
