package com.company.persons;

import java.util.Objects;

public class Person {

    private static int nrOfPersons = 0;     // Used for Auto-Incrementing the Person.id attribute

    private int id;
    private String firstName;
    private String lastName;
    private char gender;


    public Person() {
        this.id = ++Person.nrOfPersons;
    }

    public Person(String firstName, String lastName, char gender) {
        this.id = ++Person.nrOfPersons;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public Person(Person ob){               // Copy-Constructor
        this.id = ++Person.nrOfPersons;
        this.firstName = ob.getFirstName();
        this.lastName = ob.getLastName();
        this.gender = ob.getGender();
    }


    public static int getNrOfPersons() {
        return nrOfPersons;
    }

    public static void setNrOfPersons(int nrOfPersons) {
        Person.nrOfPersons = nrOfPersons;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && gender == person.gender && firstName.equals(person.firstName) && lastName.equals(person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, gender);
    }
}
