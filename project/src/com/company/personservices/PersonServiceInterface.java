package com.company.personservices;

import com.company.persons.Person;

public interface PersonServiceInterface {

    public Person readPerson();

    public void addPerson();

    public void updatePerson();

    public void deletePerson();

    public void removeTemporaryObject();
}
