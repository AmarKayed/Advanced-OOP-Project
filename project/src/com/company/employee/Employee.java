package com.company.employee;

import com.company.person.Person;

import java.util.Objects;

public class Employee extends Person {

    private float salary;
    private String hireDate;


    public Employee() {
        super();
    }


    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Float.compare(employee.salary, salary) == 0 && Objects.equals(hireDate, employee.hireDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), salary, hireDate);
    }

    @Override
    public String toString() {
        return "Employee{" +
                super.toString() +
                ", salary=" + salary +
                ", hireDate='" + hireDate + '\'' +
                '}';
    }
}
