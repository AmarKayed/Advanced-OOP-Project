package com.company.personservices;

import com.company.persons.Person;

public interface PersonServiceInterface {

    public Person readPerson(Person ob);

    public void addPerson();

    public void updatePerson();

    public void deletePerson();

    public void removeTemporaryObject();
}
