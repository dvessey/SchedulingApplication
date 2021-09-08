package com.damon.schedulingapplication.Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Appointments {
    private int Appointment_ID;
    private String Title;
    private String Description;
    private String Location;
    private String Type;
    private LocalDateTime Start;
    private LocalDateTime End;
    private LocalDateTime Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;
    private int Customer_ID;
    private int User_ID;
    private int Contact_ID;
    private String Contact_Name;
    private String Customer_Name;

    public Appointments(int appointment_ID, String title, String description, String contact, String location, String type, LocalDateTime start, LocalDateTime end, LocalDateTime create_Date, String created_By, Timestamp last_Update, String last_Updated_By, int customer_ID, int user_ID, int contact_ID) {
        Appointment_ID = appointment_ID;
        Title = title;
        Description = description;
        Contact_Name = contact;
        Location = location;
        Type = type;
        Start = start;
        End = end;
        Create_Date = create_Date;
        Created_By = created_By;
        Last_Update = last_Update;
        Last_Updated_By = last_Updated_By;
        Customer_ID = customer_ID;
        User_ID = user_ID;
        Contact_ID = contact_ID;
    }

//    public Appointments(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customer_ID, int user_ID, int contact_ID){
//        Title = title;
//        Description = description;
//        Location = location;
//        Contact_Name = contact;
//        Type = type;
//        Start = start;
//        End = end;
//        Customer_ID = customer_ID;
//        User_ID = user_ID;
//        Contact_ID = contact_ID;
//    }

    public Appointments(String title, String description, String location, String contact, String type, LocalDateTime startDateTime, LocalDateTime endDateTime, LocalDateTime createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int customerId, int userId, int contactID) {
        Title = title;
        Description = description;
        Location = location;
        Contact_Name = contact;
        Type = type;
        Start = startDateTime;
        End = endDateTime;
        Create_Date = createDate;
        Created_By = createdBy;
        Last_Update = lastUpdate;
        Last_Updated_By = lastUpdatedBy;
        Customer_ID = customerId;
        User_ID = userId;
        Contact_ID = contactID;
    }


//    public Appointments(String title, String description, String location,  String type, String contact, LocalDateTime start, LocalDateTime end, LocalDateTime create_date, String created_by, Timestamp last_update, String last_Updated_By, int customer_ID, int user_ID, int contact_ID){
//        Title = title;
//        Description = description;
//        Location = location;
//        Contact_Name = contact;
//        Create_Date = create_date;
//        Created_By = created_by;
//        Last_Update = last_update;
//        Last_Updated_By = last_Updated_By;
//        Type = type;
//        Start = start;
//        End = end;
//        Customer_ID = customer_ID;
//        User_ID = user_ID;
//        Contact_ID = contact_ID;
//    }

//    public Appointments(int id, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, LocalDateTime create_date, String created_by, Timestamp last_Update, String last_Updated_By, int customer_ID, int user_ID, int contact_ID){
//        Appointment_ID = id;
//        Title = title;
//        Description = description;
//        Location = location;
//        Type = type;
//        Start = start;
//        End = end;
//        Create_Date = create_date;
//        Created_By = created_by;
//        Last_Update = last_Update;
//        Last_Updated_By = last_Updated_By;
//        Customer_ID = customer_ID;
//        User_ID = user_ID;
//        Contact_ID = contact_ID;
//    }


    public int getAppointment_ID() {
        return Appointment_ID;
    }

    public void setAppointment_ID(int appointment_ID) {
        Appointment_ID = appointment_ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public LocalDateTime getStart() {
        return Start;
    }

    public void setStart(LocalDateTime start) {
        Start = start;
    }

    public LocalDateTime getEnd() {
        return End;
    }

    public void setEnd(LocalDateTime end) {
        End = end;
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

    public int getCustomer_ID() {
        return Customer_ID;
    }

    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }

    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }

    public int getContact_ID() {
        return Contact_ID;
    }

    public void setContact_ID(int contact_ID) {
        Contact_ID = contact_ID;
    }

    public String getContact_Name() {
        return Contact_Name;
    }

    public void setContact_Name(String contact_Name) {
        Contact_Name = contact_Name;
    }

    public String getCustomer_Name() {
        return Customer_Name;
    }

    public void setCustomer_Name(String customer_Name) {
        Customer_Name = customer_Name;
    }
}
