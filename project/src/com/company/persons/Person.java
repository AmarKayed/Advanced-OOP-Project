package com.company.persons;

import java.util.Objects;

public class Person {
    private static int nrOfPersons = 0;
    private int id;
    private String lastName;
    private String firstName;
    private char gender;

    public Person() {
        this.id = ++Person.nrOfPersons;
    }

    public Person(String lastName, String firstName, char gender) {
        this.id = ++Person.nrOfPersons;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && gender == person.gender && lastName.equals(person.lastName) && firstName.equals(person.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, gender);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", gender=" + gender +
                '}';
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}
