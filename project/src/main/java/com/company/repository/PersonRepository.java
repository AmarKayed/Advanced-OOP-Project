package com.company.repository;

import com.company.config.DatabaseConfiguration;
import com.company.person.Person;

import java.sql.*;

public class PersonRepository {

    public static PersonRepository instance;

    private PersonRepository(){}

    public static PersonRepository getInstance(){
        if (instance == null)
            instance = new PersonRepository();
        return instance;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS person " +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "firstName varchar(30), " +
                "lastName varchar(30), " +
                "gender varchar(1))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void insertPerson(Person person) {
        String insertPersonSql = "INSERT INTO person(firstName, lastName, gender) VALUES(?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql)) {
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3,person.getGender() + "");

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public Person getPersonById(int id) {
//        String selectSql = "SELECT * FROM person WHERE id=?";
//
//        Connection connection = DatabaseConfiguration.getDatabaseConnection();
//        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
//            preparedStatement.setInt(1, id);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            return mapToPerson(resultSet);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


    public void displayPerson() {
        String selectSql = "SELECT * FROM person";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) { //try with resources
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println("Id:" + resultSet.getString(1));
                System.out.println("firstName:" + resultSet.getString(2));
                System.out.println("lastName:" + resultSet.getString(3));
                System.out.println("Age:" + resultSet.getString(4));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//    private Person mapToPerson(ResultSet resultSet) throws SQLException {
//        if (resultSet.next()) {
//            return new Person(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
//        }
//        return null;
//    }


}
