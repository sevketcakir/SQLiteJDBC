package edu.pau.sqlitejdbc;

import java.sql.*;

/**
 *
 * @author Şevket Umut ÇAKIR
 */
public class SQLiteJDBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        DBHandler dbh = null;//DBHandler class to help database operations
        Connection connection = null;//Pure Connection class

        try {
            /*
             * Connect and use DBHandler class methods
             * Print all data in the table
             */
            String sql = "SELECT * FROM Employee";
            String connectionString = "jdbc:sqlite:sample.db";

            dbh = new DBHandler(connectionString);
            dbh.executeQuery(sql);
            dbh.printResultSet();
            //Randomly update department and title of the dummy entry
            dbh.executeNonQuery("UPDATE Employee SET Department='" + Math.random() + "',Title='" + Math.random() + "' WHERE Id=21");

            /*
             * Use Connection, Statement, ResultSet classes
             * Write names of employees
             */
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Employee");
            while (rs.next()) {
                System.out.println(rs.getObject("FirstName"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (dbh != null) {
                    dbh.close();
                }
            } catch (SQLException e) {
                System.out.println("Can't close DBHandler object\nReason:" + e.getMessage());
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Can't close Connection object\nReason:" + e.getMessage());
            }
        }
    }

}
