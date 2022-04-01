package com.company.personservices;

import com.company.persons.Employee;

public interface EmployeeServiceInterface{

    public Employee readEmployee();

    public void addEmployee();

    public void updateEmployee(Employee toUpdate);
}
