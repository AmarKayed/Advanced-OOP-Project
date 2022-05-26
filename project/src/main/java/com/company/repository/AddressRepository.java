package com.company.repository;

import com.company.address.Address;
import com.company.config.DatabaseConfiguration;
import com.company.transaction.Transaction;

import java.sql.*;

public class AddressRepository {
    public static AddressRepository instance;

    private AddressRepository(){}

    public static AddressRepository getInstance(){
        if (instance == null)
            instance = new AddressRepository();
        return instance;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS address " +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "country varchar(30), " +
                "city varchar(30))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void insertAddress(Address address) {
        String insertAddressSql = "INSERT INTO transaction(iban, transactionDate, amount) VALUES(?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertAddressSql)) {
            preparedStatement.setString(1, address.getCountry());
            preparedStatement.setString(2, address.getCity());


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Address getAddressById(int id) {
        String selectSql = "SELECT * FROM address WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToAddress(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void displayAddress() {
        String selectSql = "SELECT * FROM address";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) { //try with resources
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println("Id:" + resultSet.getInt(0));
                System.out.println("Country" + resultSet.getString(1));
                System.out.println("City" + resultSet.getString(2));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAddress(String country, String city,  int id) {
        String updateAddressSql = "UPDATE address SET country=? AND city=? WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateAddressSql)) {
            preparedStatement.setString(1, country);
            preparedStatement.setString(2, city);
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        String query = "DELETE FROM transaction WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseConfiguration.closeDatabaseConnection();

    }

    private Address mapToAddress(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Address(resultSet.getString(1), resultSet.getString(2));
        }
        return null;
    }
}
