package com.damon.schedulingapplication.DAO;

import com.damon.schedulingapplication.Controller.LoginController;
import com.damon.schedulingapplication.Model.Users;
import com.damon.schedulingapplication.utils.DbConnection;
import com.damon.schedulingapplication.utils.DbQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * UserDaoImpl class to handle user database transactions
 * @author Damon Vessey
 */
public class UserDaoImpl {
    /**
     * getUser method to get user by user name
     * @param userName
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Users getUser(String userName) throws ClassNotFoundException, SQLException {
        Connection conn = DbConnection.startConnection();
        String selectStatement = "SELECT * FROM users WHERE User_Name = '" + userName + "'";
        DbQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement ps = DbQuery.getPreparedStatement();

        ps.execute();

        Users userResult;
        //Users theUser = LoginController.getTheUser();

        ResultSet rs = ps.getResultSet();

        while(rs.next()){
            int userId = rs.getInt("User_ID");
            System.out.println("UserID in getUser: " + userId);
            //theUser.setUserID(userId);
            System.out.println("UserID in getUser after setUser: " + userId); //logging in as admin is showing 2 correct
            String user_name = rs.getString("User_Name");
            String password = rs.getString("Password");
            LocalDateTime createDate = rs.getObject("Create_Date", LocalDateTime.class);
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");

            userResult = new Users(userId, user_name, password, createDate, createdBy, lastUpdate, lastUpdatedBy);

            return userResult;
        }
        DbConnection.closeConnection();
        return null;
    }

    public static ObservableList<Users> getUsers() throws ClassNotFoundException, SQLException {
        Connection conn = DbConnection.startConnection();
        String selectStatement = "SELECT * FROM users";
        DbQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement ps = DbQuery.getPreparedStatement();

        ps.execute();

       ObservableList<Users> allUsers = FXCollections.observableArrayList();

        ResultSet rs = ps.getResultSet();

        while(rs.next()){
            int userId = rs.getInt("User_ID");
           // System.out.println("UserID in getUser: " + userId);
            //System.out.println("UserID in getUser after setUser: " + userId); //logging in as admin is showing 2 correct
            String user_name = rs.getString("User_Name");
            String password = rs.getString("Password");
            LocalDateTime createDate = rs.getObject("Create_Date", LocalDateTime.class);
            String createdBy = rs.getString("Created_By");
            Timestamp lastUpdate = rs.getTimestamp("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");

             allUsers.add(new Users(userId, user_name, password, createDate, createdBy, lastUpdate, lastUpdatedBy));
        }
        DbConnection.closeConnection();
        return allUsers;
    }
}
