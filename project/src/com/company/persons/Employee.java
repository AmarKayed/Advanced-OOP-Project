package com.company.persons;

public class Employee extends Person{

    private float salary;
    private String hireDate;

    public Employee() {
        super();
    }
    public Employee(String firstName, String lastName, char gender) {
        super(firstName, lastName, gender);
    }
    public Employee(float salary, String hireDate) {
        this.salary = salary;
        this.hireDate = hireDate;
    }
}
