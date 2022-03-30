package com.company.personservices;

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

    public void addPerson(){

    }
}
