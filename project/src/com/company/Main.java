package com.company;

import com.company.persons.Person;

public class Main {

    public static void main(String[] args) {

        String y = new String("something");
        Person x = new Person(y, y, 'M');
        y = "else";
        System.out.println(x);
        Person z = new Person();
        System.out.println(z);
    }
}