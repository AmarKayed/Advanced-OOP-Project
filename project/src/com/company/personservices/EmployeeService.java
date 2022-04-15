package com.company.personservices;

import com.company.Service;
import com.company.persons.Employee;
import com.company.persons.Person;

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
//        Person p = PersonService.getInstance().readPerson();    // We don't need to create a new object, we can reference the returned one
//        System.out.println("HERE " + p.getId());

        Employee ob = new Employee();
        PersonService.getInstance().readPerson(ob);

        float salary = 0;
        boolean badInput = true;
        String input;

        System.out.println("Salary: ");
        while (badInput){
            input = Service.getInstance().getSc().nextLine();
            try {
                salary = Float.parseFloat(input);
                if(salary < 0)
                    System.out.println("The salary cannot be negative. Please type another salary.");
                else badInput = false;
            }
            catch (NumberFormatException e){
                System.out.println("The salary MUST be a positive real number. Please try again:");
            }
        }

        ob.setSalary(salary);

        System.out.println("hireDate: ");
        String hireDate;
        hireDate = Service.getInstance().getSc().nextLine();
        ob.setHireDate(hireDate);

        return ob;
    }

    @Override
    public void addEmployee() {
//        Employee ob = new Employee("Michael", "Jack", 'M', 2000, "22 MAR 2022");

        Employee ob = EmployeeService.getInstance().readEmployee();

        PersonService.getInstance().getPersonList().add(ob);
    }



    @Override
    public void updateEmployee(Employee toUpdate) {
        System.out.println("Salary: " + toUpdate.getSalary());
        System.out.println("Salary Update Value: ");
        String salary;
        float actualSalary;
        boolean badInput = true;
        salary = Service.getInstance().getSc().nextLine();

        if(!salary.equals("_keep"))
            while(badInput)
                try{
                    String[] splited = salary.split("\\s+");
                    actualSalary = Float.parseFloat(splited[0]);
                    System.out.println(actualSalary);
                    if (salary.equals("_keep"))
                        badInput = false;
                    else if(actualSalary >= 0){
                        badInput = false;
                        toUpdate.setSalary(actualSalary);
                    }
                    else{
                        System.out.println("The salary cannot be negative!");
                    }

                }
                catch(java.lang.NumberFormatException e){
                    System.out.println("Format exception");
                    System.out.println("Please make sure that the first value before space is an actual floating point number.");
                    salary = Service.getInstance().getSc().nextLine();
                    badInput = true;
                }

        System.out.println("Hire Date: " + toUpdate.getHireDate());
        System.out.println("Hire Date Update Value: ");
        String hireDate;
        hireDate = Service.getInstance().getSc().nextLine();

        if(!hireDate.equals("_keep"))
            toUpdate.setHireDate(hireDate);

    }
}
