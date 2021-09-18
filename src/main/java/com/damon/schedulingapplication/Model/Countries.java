package com.damon.schedulingapplication.Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Countries class
 * @author Damon Vessey
 */
public class Countries {
    private int Country_ID;
    private String Country;
    private LocalDateTime Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;

    /**
     * Countries constructor
     * @param country_ID
     * @param country
     * @param create_Date
     * @param created_By
     * @param last_Update
     * @param last_Updated_By
     */
    public Countries(int country_ID, String country, LocalDateTime create_Date, String created_By, Timestamp last_Update, String last_Updated_By) {
        Country_ID = country_ID;
        Country = country;
        Create_Date = create_Date;
        Created_By = created_By;
        Last_Update = last_Update;
        Last_Updated_By = last_Updated_By;
    }

    /**
     * Countries constructor
     * @param country_ID
     * @param country
     */
    public Countries(int country_ID, String country){
        Country_ID = country_ID;
        Country = country;
    }

    /**
     * getCountry_ID method to get country id
     * @return
     */
    public int getCountry_ID() {
        return Country_ID;
    }

    /**
     * setCountry_ID method to set country id
     * @param country_ID
     */
    public void setCountry_ID(int country_ID) {
        Country_ID = country_ID;
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
     * @param country
     */
    public void setCountry(String country) {
        Country = country;
    }

    /**
     * getCreate_date method to get date created
     * @return
     */
    public LocalDateTime getCreate_Date() {
        return Create_Date;
    }

    /**
     * setCreate_date method to set date created
     * @param create_Date
     */
    public void setCreate_Date(LocalDateTime create_Date) {
        Create_Date = create_Date;
    }

    /**
     * getCreate_By method to get created by
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
     * setLastUpdate method to set last updated
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
     * toString method to return object as country name
     * @return
     */
    @Override
    public String toString(){
        return Country;
    }
}
