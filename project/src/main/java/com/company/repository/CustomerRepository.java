package com.company.repository;

import com.company.address.Address;
import com.company.config.DatabaseConfiguration;
import com.company.customer.Customer;
import com.company.person.Person;

import java.sql.*;

public class CustomerRepository {
    public static CustomerRepository instance;

    private CustomerRepository(){}

    public static CustomerRepository getInstance(){
        if (instance == null)
            instance = new CustomerRepository();
        return instance;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS customer " +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "firstName varchar(30), " +
                "lastName varchar(30), " +
                "gender varchar(1), " +
                "country varchar(30), " +
                "city varchar(30), " +
                "job varchar(30))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void insertCustomer(Customer customer) {
        String insertCustomerSql = "INSERT INTO customer(firstName, lastName, gender, country, city, job) VALUES(?, ?, ?, ?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertCustomerSql)) {
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3,customer.getGender() + "");
            preparedStatement.setString(4,customer.getAddress().getCountry());
            preparedStatement.setString(5,customer.getAddress().getCity());
            preparedStatement.setString(6,customer.getJob());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Customer getCustomerById(int id) {
        String selectSql = "SELECT * FROM customer WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToCustomer(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void displayCustomer() {
        String selectSql = "SELECT * FROM customer";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) { //try with resources
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println("Id:" + resultSet.getString(1));
                System.out.println("firstName:" + resultSet.getString(2));
                System.out.println("lastName:" + resultSet.getString(3));
                System.out.println("gender:" + resultSet.getString(4));
                System.out.println("Address:" + resultSet.getString(5) + ", " + resultSet.getString(6));
                System.out.println("Job:" + resultSet.getString(7));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCustomerName(String name, int id) {
        String updateNameSql = "UPDATE customer SET name=? WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        String updateNameSql = "DELETE FROM customer WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseConfiguration.closeDatabaseConnection();

    }

    private Customer mapToCustomer(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Customer(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3).charAt(0), new Address(resultSet.getString(4), resultSet.getString(5)), resultSet.getString(6));
        }
        return null;
    }

}
