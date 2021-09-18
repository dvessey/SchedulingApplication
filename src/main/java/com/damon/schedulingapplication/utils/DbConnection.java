package com.damon.schedulingapplication.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DbConnection class to handle database connections
 * @author Damon Vessey
 */
public class DbConnection {

    //JDBC URL
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String ip = "//wgudb.ucertify.com/WJ08VKu";
    private static final String jdbcUrl = protocol + vendor + ip;

    // Driver Interface Reference
    private static final String MYSQLJDBCDriver = "com.mysql.jdbc.Driver";
    private static Connection conn = null;

    //Username
    private static final String userName = "U08VKu";

    //Password
    private static final String password = "53689403315";

    /**
     * startConnection method to start database connection
     * @return
     * @throws ClassNotFoundException
     */
    public static Connection startConnection() throws ClassNotFoundException {
        try{
            Class.forName(MYSQLJDBCDriver);
            conn = DriverManager.getConnection(jdbcUrl, userName, password);
            System.out.println("Connection Successful!");
        }
        catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return conn;
    }

    /**
     * closeConnection method to close database connection
     * @throws SQLException
     */
    public static void closeConnection() throws SQLException {
        conn.close();
        System.out.println("Connection Closed!");
    }
}
