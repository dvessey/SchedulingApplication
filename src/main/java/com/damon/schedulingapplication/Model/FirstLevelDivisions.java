package com.damon.schedulingapplication.Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * FirstLevelDivisions class
 * @author Damon Vessey
 */
public class FirstLevelDivisions {
    private int Division_ID;
    private String Division;
    private LocalDateTime Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;
    private int Country_ID;

    /**
     * FirstLevelDivisions constructor
     * @param division_ID
     * @param division
     * @param create_Date
     * @param created_By
     * @param last_Update
     * @param last_Updated_By
     * @param country_ID
     */
    public FirstLevelDivisions(int division_ID, String division, LocalDateTime create_Date, String created_By, Timestamp last_Update, String last_Updated_By, int country_ID) {
        Division_ID = division_ID;
        Division = division;
        Create_Date = create_Date;
        Created_By = created_By;
        Last_Update = last_Update;
        Last_Updated_By = last_Updated_By;
        Country_ID = country_ID;
    }

    /**
     * FirstLevelDivisions constructor
     * @param division_ID
     * @param division
     * @param country_ID
     */
    public FirstLevelDivisions(int division_ID, String division, int country_ID){
        Division_ID = division_ID;
        Division = division;
        Country_ID = country_ID;
    }

    /**
     * getDivisionID method to get division id
     * @return
     */
    public int getDivision_ID() {
        return Division_ID;
    }

    /**
     * setDivisionID method to set division ID
     * @param division_ID
     */
    public void setDivision_ID(int division_ID) {
        Division_ID = division_ID;
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
     * getCreateDate method to get create date
     * @return
     */
    public LocalDateTime getCreate_Date() {
        return Create_Date;
    }

    /**
     * setCreateDate method to set create date
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
     * getLastUpdate method to get last update
     * @return
     */
    public Timestamp getLast_Update() {
        return Last_Update;
    }

    /**
     * setLastUpdate method to set last update
     * @param last_Update
     */
    public void setLast_Update(Timestamp last_Update) {
        Last_Update = last_Update;
    }

    /**
     * getLastUpdatedBy method to get last updated by
     * @return
     */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /**
     * setLastUpdatedBy method to set last updated by
     * @param last_Updated_By
     */
    public void setLast_Updated_By(String last_Updated_By) {
        Last_Updated_By = last_Updated_By;
    }

    /**
     * getCountryId method to get country id
     * @return
     */
    public int getCountry_ID() {
        return Country_ID;
    }

    /**
     * setCountryId method to set country id
     * @param country_ID
     */
    public void setCountry_ID(int country_ID) {
        Country_ID = country_ID;
    }

    /**
     * toString method to return object as division name
     * @return
     */
    @Override
    public String toString(){
        return Division;
    }
}
