package com.damon.schedulingapplication.Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Customers class
 * @author Damon Vessey
 */
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

    /**
     * Customers constructor
     * @param customerId
     * @param customer_Name
     * @param address
     * @param postal_Code
     * @param phone
     * @param create_Date
     * @param created_By
     * @param last_Update
     * @param last_Updated_By
     * @param division
     * @param country_Name
     * @param country_ID
     * @param division_ID
     */
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

    /**
     * Customers constructor
     * @param customer_Name
     * @param address
     * @param postal_Code
     * @param phone
     * @param create_Date
     * @param created_By
     * @param last_Update
     * @param last_Updated_By
     * @param country_Name
     * @param country_ID
     * @param division_ID
     */
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

    /**
     * Customers constructor
     * @param customer_ID
     * @param customer_Name
     * @param address
     * @param postal_Code
     * @param phone
     * @param create_Date
     * @param created_By
     * @param last_Update
     * @param last_Updated_By
     * @param division_ID
     */
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

    /**
     * Customers constructor
     * @param customerName
     * @param address
     * @param postalCode
     * @param phoneNumber
     * @param createDate
     * @param createdBy
     * @param lastUpdated
     * @param lastUpdatedBy
     * @param divisionId
     */
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

    /**
     * Customers constructor
     * @param customerID
     * @param customerName
     */
    public Customers(int customerID, String customerName) {
        Customer_ID = customerID;
        Customer_Name = customerName;
    }

    /**
     * getCustomerId method to get customer id
     * @return
     */
    public int getCustomer_ID() {
        return Customer_ID;
    }

    /**
     * setCustomerId method to set customer id
     * @param customer_ID
     */
    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }

    /**
     * getCustomerName method to get customer name
     * @return
     */
    public String getCustomer_Name() {
        return Customer_Name;
    }

    /**
     * setCustomerName method to set customer name
     * @param customer_Name
     */
    public void setCustomer_Name(String customer_Name) {
        Customer_Name = customer_Name;
    }

    /**
     * getAddress method to get address
     * @return
     */
    public String getAddress() {
        return Address;
    }

    /**
     * setAddress method to set address
     * @param address
     */
    public void setAddress(String address) {
        Address = address;
    }

    /**
     * getPostalCode method to get postal code
     * @return
     */
    public String getPostal_Code() {
        return Postal_Code;
    }

    /**
     * setPostalCode method to set postal code
     * @param postal_Code
     */
    public void setPostal_Code(String postal_Code) {
        Postal_Code = postal_Code;
    }

    /**
     * getPhone method to get phone number
     * @return
     */
    public String getPhone() {
        return Phone;
    }

    /**
     * setPhone method to set phone number
     * @param phone
     */
    public void setPhone(String phone) {
        Phone = phone;
    }

    /**
     * getCreate_Date method to get date created
     * @return
     */
    public LocalDateTime getCreate_Date() {
        return Create_Date;
    }

    /**
     * setCreate_Date method to set date created
     * @param create_Date
     */
    public void setCreate_Date(LocalDateTime create_Date) {
        Create_Date = create_Date;
    }

    /**
     * getCreated_by method to get created by
     * @return
     */
    public String getCreated_By() {
        return Created_By;
    }

    /**
     * setCreated_By method to set created by
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
     * setLast_Update method to set last update
     * @param last_Update
     */
    public void setLast_Update(Timestamp last_Update) {
        Last_Update = last_Update;
    }

    /**
     * getLast_updated_by method to get last updated by
     * @return
     */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /**
     * setLast_Update_by method to set last updated by
     * @param last_Updated_By
     */
    public void setLast_Updated_By(String last_Updated_By) {
        Last_Updated_By = last_Updated_By;
    }

    /**
     * getDivision_ID method to get division id
     * @return
     */
    public int getDivision_ID() {
        return Division_ID;
    }

    /**
     * setDivision_ID method to set division id
     * @param division_ID
     */
    public void setDivision_ID(int division_ID) {
        Division_ID = division_ID;
    }

    /**
     * getCountry method to get country
     * @return
     */
    public String getCountry() {
        return Country;
    }

    /**
     * setCountry method to set country
     * @param country_Name
     */
    public void setCountry(String country_Name) {
        Country = country_Name;
    }

    /**
     * getCountryID method to get country od
     * @return
     */
    public int getCountry_ID() {
        return Country_ID;
    }

    /**
     * setCountryID method to set country id
     * @param country_ID
     */
    public void setCountry_ID(int country_ID) {
        Country_ID = country_ID;
    }

    /**
     * getDivision method to get division
     * @return
     */
    public String getDivision() {
        return Division;
    }

    /**
     * setDivision method to set division
     * @param division
     */
    public void setDivision(String division) {
        Division = division;
    }

    /**
     * toString method to return object as customer name
     * @return
     */

    @Override
    public String toString(){
        return Customer_Name;
    }
}
