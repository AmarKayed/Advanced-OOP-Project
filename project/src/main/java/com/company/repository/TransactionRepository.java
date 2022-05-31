package com.company.repository;

import com.company.config.DatabaseConfiguration;
import com.company.transaction.Transaction;

import java.sql.*;

public class TransactionRepository {
    public static TransactionRepository instance;

    private TransactionRepository(){}

    public static TransactionRepository getInstance(){
        if (instance == null)
            instance = new TransactionRepository();
        return instance;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS transaction " +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "iban int, " +
                "transactionDate varchar(30), " +
                "amount float(10,2))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void insertTransaction(Transaction transaction) {
        String insertTransactionSql = "INSERT INTO transaction(iban, transactionDate, amount) VALUES(?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertTransactionSql)) {
            preparedStatement.setInt(1, transaction.getIban());
            preparedStatement.setString(2, transaction.getTransactionDate());
            preparedStatement.setFloat(3,transaction.getAmount());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Transaction getTransactionById(int id) {
        String selectSql = "SELECT * FROM transaction WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToTransaction(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void displayTransaction() {
        String selectSql = "SELECT * FROM transaction";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) { //try with resources
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println("IBAN:" + resultSet.getInt(1));
                System.out.println("transactionDate" + resultSet.getString(2));
                System.out.println("Amount" + resultSet.getFloat(3));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTransactionDate(String transactionDate, int id) {
        String updateTransactionDateSql = "UPDATE transaction SET transactionDate=? WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateTransactionDateSql)) {
            preparedStatement.setString(1, transactionDate);
            preparedStatement.setInt(2, id);

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

    private Transaction mapToTransaction(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Transaction(resultSet.getInt(1), resultSet.getString(2), resultSet.getFloat(3));
        }
        return null;
    }

}
