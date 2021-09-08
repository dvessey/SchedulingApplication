package com.damon.schedulingapplication.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


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

    public static void closeConnection() throws SQLException {
        conn.close();
        System.out.println("Connection Closed!");
    }
}
