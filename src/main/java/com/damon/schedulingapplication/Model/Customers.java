package com.damon.schedulingapplication.Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Customers {
    private int Customer_ID;
    private String Customer_Name;
    private String Address;
    private String Postal_Code;
    private String Phone;
    private LocalDateTime Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;
    private String Division;
    private String Country;
    private int Country_ID;
    private int Division_ID;

    public Customers(int customerId, String customer_Name, String address, String postal_Code, String phone, LocalDateTime create_Date, String created_By, Timestamp last_Update, String last_Updated_By, String division, String country_Name, int country_ID, int division_ID) {
        Customer_ID = customerId;
        Customer_Name = customer_Name;
        Address = address;
        Postal_Code = postal_Code;
        Phone = phone;
        Create_Date = create_Date;
        Created_By = created_By;
        Last_Update = last_Update;
        Last_Updated_By = last_Updated_By;
        Division = division;
        Country = country_Name;
        Country_ID = country_ID;
        Division_ID = division_ID;
    }

    public Customers(String customer_Name, String address, String postal_Code, String phone, LocalDateTime create_Date, String created_By, Timestamp last_Update, String last_Updated_By, String country_Name, int country_ID, int division_ID) {
        Customer_Name = customer_Name;
        Address = address;
        Postal_Code = postal_Code;
        Phone = phone;
        Create_Date = create_Date;
        Created_By = created_By;
        Last_Update = last_Update;
        Last_Updated_By = last_Updated_By;
        Country = country_Name;
        Country_ID = country_ID;
        Division_ID = division_ID;
    }


    public Customers(int customer_ID, String customer_Name, String address, String postal_Code, String phone, LocalDateTime create_Date, String created_By, Timestamp last_Update, String last_Updated_By, int division_ID) {
        Customer_ID = customer_ID;
        Customer_Name = customer_Name;
        Address = address;
        Postal_Code = postal_Code;
        Phone = phone;
        Create_Date = create_Date;
        Created_By = created_By;
        Last_Update = last_Update;
        Last_Updated_By = last_Updated_By;
        Division_ID = division_ID;
    }

    public Customers(String customerName, String address, String postalCode, String phoneNumber, LocalDateTime createDate, String createdBy, Timestamp lastUpdated, String lastUpdatedBy, int divisionId) {
        Customer_Name = customerName;
        Address = address;
        Postal_Code = postalCode;
        Phone = phoneNumber;
        Create_Date = createDate;
        Created_By = createdBy;
        Last_Update = lastUpdated;
        Last_Updated_By = lastUpdatedBy;
        Division_ID = divisionId;
    }

    public Customers(int customerID, String customerName) {
        Customer_ID = customerID;
        Customer_Name = customerName;
    }

    public int getCustomer_ID() {
        return Customer_ID;
    }

    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }

    public String getCustomer_Name() {
        return Customer_Name;
    }

    public void setCustomer_Name(String customer_Name) {
        Customer_Name = customer_Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPostal_Code() {
        return Postal_Code;
    }

    public void setPostal_Code(String postal_Code) {
        Postal_Code = postal_Code;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public LocalDateTime getCreate_Date() {
        return Create_Date;
    }

    public void setCreate_Date(LocalDateTime create_Date) {
        Create_Date = create_Date;
    }

    public String getCreated_By() {
        return Created_By;
    }

    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }

    public Timestamp getLast_Update() {
        return Last_Update;
    }

    public void setLast_Update(Timestamp last_Update) {
        Last_Update = last_Update;
    }

    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    public void setLast_Updated_By(String last_Updated_By) {
        Last_Updated_By = last_Updated_By;
    }

    public int getDivision_ID() {
        return Division_ID;
    }

    public void setDivision_ID(int division_ID) {
        Division_ID = division_ID;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country_Name) {
        Country = country_Name;
    }

    public int getCountry_ID() {
        return Country_ID;
    }

    public void setCountry_ID(int country_ID) {
        Country_ID = country_ID;
    }

    public String getDivision() {
        return Division;
    }

    public void setDivision(String division) {
        Division = division;
    }



    @Override
    public String toString(){
        return Customer_Name;
    }
}
