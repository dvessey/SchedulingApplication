package com.damon.schedulingapplication.Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Users class
 * @author Damon Vessey
 */
public class Users {
    private  int UserID;
    private  String User_name;
    private String Password;
    private LocalDateTime Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;

    /**
     * Users constructor
     * @param userID
     * @param user_name
     * @param password
     * @param create_Date
     * @param created_By
     * @param last_Update
     * @param last_Updated_By
     */
    public Users(int userID, String user_name, String password, LocalDateTime create_Date, String created_By, Timestamp last_Update, String last_Updated_By) {
        UserID = userID;
        User_name = user_name;
        Password = password;
        Create_Date = create_Date;
        Created_By = created_By;
        Last_Update = last_Update;
        Last_Updated_By = last_Updated_By;
    }

    /**
     * Users Constructor
     * @param userID
     */
    public Users(int userID){
        UserID = userID;
    }

    /**
     * getUserId method to get user id
     * @return
     */
    public  int getUserID() {
        return UserID;
    }

    /**
     * setUserId method to set user id
     * @param userID
     */
    public  void setUserID(int userID) {
        UserID = userID;
    }

    /**
     * getUserName method to get user name
     * @return
     */
    public  String getUser_name() {
        return User_name;
    }

    /**
     * setUserName method to set user name
     * @param user_name
     */
    public  void setUser_name(String user_name) {
        User_name = user_name;
    }

    /**
     * getPassword method to get password
     * @return
     */
    public String getPassword() {
        return Password;
    }

    /**
     * setPassword method to set password
     * @param password
     */
    public void setPassword(String password) {
        Password = password;
    }

    /**
     * getCreate_Date method to get created date
     * @return
     */
    public LocalDateTime getCreate_Date() {
        return Create_Date;
    }

    /**
     * setCreate_Date method to set created date
     * @param create_Date
     */
    public void setCreate_Date(LocalDateTime create_Date) {
        Create_Date = create_Date;
    }

    /**
     * getCreatedBy method to get created by
     * @return
     */
    public String getCreated_By() {
        return Created_By;
    }

    /**
     * setCreatedBy method to set created by
     * @param created_By
     */
    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }

    /**
     * getLast_Update method to get last update
     * @return
     */
    public Timestamp getLast_Update() {
        return Last_Update;
    }

    /**
     * setLastUpdate method to st last update
     * @param last_Update
     */
    public void setLast_Update(Timestamp last_Update) {
        Last_Update = last_Update;
    }

    /**
     * getLast_Updated_By method to get last updated by
     * @return
     */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /**
     * setLast_Updated_By method to set last updated by
     * @param last_Updated_By
     */
    public void setLast_Updated_By(String last_Updated_By) {
        Last_Updated_By = last_Updated_By;
    }

    @Override
    public String toString(){
        return User_name;
    }
}
