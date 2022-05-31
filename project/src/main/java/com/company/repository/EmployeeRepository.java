package com.company.repository;

import com.company.config.DatabaseConfiguration;
import com.company.employee.Employee;

import java.sql.*;

public class EmployeeRepository {
    public static EmployeeRepository instance;

    private EmployeeRepository(){}

    public static EmployeeRepository getInstance(){
        if (instance == null)
            instance = new EmployeeRepository();
        return instance;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS employee " +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "firstName varchar(30), " +
                "lastName varchar(30), " +
                "gender varchar(1), " +
                "salary float(10,2), " +
                "hireDate varchar(20))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void insertEmployee(Employee employee) {
        String insertEmployeeSql = "INSERT INTO employee(firstName, lastName, gender, salary, hireDate) VALUES(?, ?, ?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertEmployeeSql)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3,employee.getGender() + "");
            preparedStatement.setFloat(4,employee.getSalary());
            preparedStatement.setString(5,employee.getHireDate());


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Employee getEmployeeById(int id) {
        String selectSql = "SELECT * FROM employee WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToEmployee(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void displayEmployee() {
        String selectSql = "SELECT * FROM employee";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) { //try with resources
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println("Id:" + resultSet.getString(1));
                System.out.println("firstName:" + resultSet.getString(2));
                System.out.println("lastName:" + resultSet.getString(3));
                System.out.println("gender:" + resultSet.getString(4));
                System.out.println("salary:" + resultSet.getFloat(5));
                System.out.println("hireDate:" + resultSet.getString(6));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateEmployee(String firstName, String lastName, char gender, float salary, String hireDate, int id){
        String updateNameSql = "UPDATE employee SET firstName=?, lastName=?, gender=?, salary=?, hireDate=? WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, gender + "");
            preparedStatement.setFloat(4, salary);
            preparedStatement.setString(5, hireDate);
            preparedStatement.setInt(6, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        String updateNameSql = "DELETE FROM employee WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseConfiguration.closeDatabaseConnection();

    }

    private Employee mapToEmployee(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Employee(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3).charAt(0), resultSet.getFloat(4), resultSet.getString(5));
        }
        return null;
    }


}
