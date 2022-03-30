package com.company;

import com.company.persons.Customer;
import com.company.persons.Employee;
import com.company.persons.Person;

public class Main {

    public static void main(String[] args) {

        String y = new String("something");
        Person x = new Person(y, y, 'M');
        y = "else";
        System.out.println(x);
        Person z = new Person();
        System.out.println(z);

        Address a = new Address("US", "New York City");
        System.out.println(a);
        Customer c = new Customer(x, a, "Lawyer");
        x.setLastName("Here");
        System.out.println(c);
        System.out.println(x);

        Employee emp = new Employee(x, 3000, "25 MAR 2022");
        System.out.println(emp);
    }
}
