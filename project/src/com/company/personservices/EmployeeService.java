package com.company.personservices;

import com.company.persons.Employee;
import com.company.persons.Person;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService implements EmployeeServiceInterface{

    private static EmployeeService instance;

    private EmployeeService() {}

    public static EmployeeService getInstance() {
        if(instance == null)
            instance = new EmployeeService();
        return instance;
    }

    @Override
    public void addEmployee() {
        Employee ob = new Employee("Michael", "Jack", 'M', 2000, "22 MAR 20222");

        PersonService.getInstance().getPersonList().add(ob);
    }
}
