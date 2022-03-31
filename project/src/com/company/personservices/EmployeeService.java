package com.company.personservices;

import com.company.Service;
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
    public Employee readEmployee(){
        Person p = PersonService.getInstance().readPerson();    // We don't need to create a new object, we can reference the returned one
//        System.out.println("HERE " + p.getId());
        float salary;
        String hireDate;
        boolean badInput = false;

        System.out.println("Salary: ");
        salary = Service.getInstance().getSc().nextFloat();
        Service.getInstance().getSc().nextLine(); // clear the buffer


        if(salary < 0)
            badInput = true;
        while(badInput){

            System.out.println("The salary cannot be negative. Please type another salary.");
            System.out.println("Salary: ");

            salary = Service.getInstance().getSc().nextFloat();
            Service.getInstance().getSc().nextLine(); // clear the buffer

            if(salary >= 0)
                badInput = false;
        }

        System.out.println("hireDate: ");
        hireDate = Service.getInstance().getSc().nextLine();

        Employee ob = new Employee(p, salary, hireDate);
//        PersonService.getInstance().removeTemporaryObject();    // So that the ID's remain in normal logical order
        return ob;
    }

    @Override
    public void addEmployee() {
//        Employee ob = new Employee("Michael", "Jack", 'M', 2000, "22 MAR 2022");

        Employee ob = EmployeeService.getInstance().readEmployee();

        PersonService.getInstance().getPersonList().add(ob);
    }
}
