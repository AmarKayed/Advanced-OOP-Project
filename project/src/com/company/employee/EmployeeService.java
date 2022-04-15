package com.company.employee;

import com.company.employee.Employee;

public interface EmployeeService {

    public Employee readEmployee();

    public void addEmployee();

    public void updateEmployee(Employee toUpdate);
}
