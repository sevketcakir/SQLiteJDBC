/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pau.sqlitejdbc;

import java.sql.*;

/**
 *
 * @author Şevket Umut ÇAKIR
 */
public class DBHandler {

    private String connectionString;
    private Connection connection;
    private ResultSet rs;

    public DBHandler() {
        connectionString = "";
    }

    public DBHandler(String connectionString) throws SQLException {
        this.connectionString = connectionString;
        this.connect();
    }

    /**
     * Sets the connection string
     *
     * @param connectionString the connectionString to set
     */
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    /**
     * Connects the database specified in connectionString
     * @throws SQLException 
     */
    public final void connect() throws SQLException {
        connection = DriverManager.getConnection(connectionString);
    }

    /**
     * Closes database connection
     * @throws SQLException 
     */
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * Executes a SELECT query
     * @param query SQL SELECT statement to query
     * @return ResultSet object containing the result set of query
     * @throws SQLException 
     */
    public ResultSet executeQuery(String query) throws SQLException {
        if (connection != null) {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
        }
        return rs;
    }

    /**
     * Execute a DELETE,INSERT or an UPDATE query
     * @param sql SQL Statement to execute
     * @throws SQLException 
     */
    public int executeNonQuery(String sql) throws SQLException {
        if (connection != null) {
            Statement stmt = connection.createStatement();
            return stmt.executeUpdate(sql);
        }
        return -1;
    }

    /**
     * Prints the Resultsel to standart output
     */
    public void printResultSet() {
        if (rs != null) {
            try {
                ResultSetMetaData md = rs.getMetaData();
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    System.out.printf("%-35s\t", md.getColumnName(i));
                }
                System.out.println();
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    System.out.printf("%-35s\t", "------------------------");
                }
                System.out.println();
                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.printf("%-35s\t", rs.getObject(i));
                    }
                    System.out.println();
                }
            } catch (SQLException e) {
                System.out.println("SQLException occured\nError message:" + e.getMessage());
            }

        }
    }
}
