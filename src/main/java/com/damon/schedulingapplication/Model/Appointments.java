package com.damon.schedulingapplication.Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;

/**
 * Appointments class
 * @author Damon Vessey
 */
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
    private String startMonth;
    private int Count;

    /**
     * Appointments Constructor
     * @param appointment_ID
     * @param title
     * @param description
     * @param contact
     * @param location
     * @param type
     * @param start
     * @param end
     * @param create_Date
     * @param created_By
     * @param last_Update
     * @param last_Updated_By
     * @param customer_ID
     * @param user_ID
     * @param contact_ID
     */
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

    /**
     * Appointments constructor
     * @param title
     * @param description
     * @param contact
     * @param location
     * @param type
     * @param startDateTime
     * @param endDateTime
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param customerId
     * @param userId
     * @param contactID
     */
    public Appointments(String title, String description, String contact, String location, String type, LocalDateTime startDateTime, LocalDateTime endDateTime, LocalDateTime createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int customerId, int userId, int contactID) {
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

    /**
     * Appointments constructor
     * @param id
     * @param title
     * @param type
     * @param description
     * @param start
     * @param end
     * @param customerId
     */
    public Appointments(int id, String title, String type, String description, LocalDateTime start, LocalDateTime end, int customerId ){
        Appointment_ID = id;
        Title = title;
        Type = type;
        Description = description;
        Start = start;
        End = end;
        Customer_ID = customerId;
    }

    /**
     * Appointments constructor
     * @param start
     * @param type
     * @param count
     */
    public Appointments(String start, String type, int count){
        startMonth = start;
        Type = type;
        Count = count;
    }

    /**
     * Appointments constructor
     * @param location
     * @param count
     */
    public Appointments(String location, int count){
        Location = location;
        Count = count;
    }

    /**
     * Appointments constructor
     * @param appointmentId
     * @param title
     * @param description
     * @param contact
     * @param type
     * @param startTs
     * @param endTs
     * @param customerId
     * @param contactId
     */
    public Appointments(int appointmentId, String title, String description, String contact, String type, LocalDateTime startTs, LocalDateTime endTs, int customerId, int contactId) {
        Appointment_ID = appointmentId;
        Title = title;
        Description = description;
        Contact_Name = contact;
        Type = type;
        Start = startTs;
        End = endTs;
        Customer_ID = customerId;
        Contact_ID = contactId;
    }

    /**
     * getAppointment_ID method to get appointment Id
     * @return
     */
    public int getAppointment_ID() {
        return Appointment_ID;
    }

    /**
     * setAppointmetn_ID method to set appointment Id
     * @param appointment_ID
     */
    public void setAppointment_ID(int appointment_ID) {
        Appointment_ID = appointment_ID;
    }

    /**
     * getTitle method to get title
     * @return
     */
    public String getTitle() {
        return Title;
    }

    /**
     * setTitle method to set title
     * @param title
     */
    public void setTitle(String title) {
        Title = title;
    }

    /**
     * getDescription method to get description
     * @return
     */
    public String getDescription() {
        return Description;
    }

    /**
     * setDescription method to set description
     * @param description
     */
    public void setDescription(String description) {
        Description = description;
    }

    /**
     * getLocation method to get location
     * @return
     */
    public String getLocation() {
        return Location;
    }

    /**
     * setLocation method to set location
     * @param location
     */
    public void setLocation(String location) {
        Location = location;
    }

    /**
     * getType method to get type
     * @return
     */
    public String getType() {
        return Type;
    }

    /**
     * setType method to set type
     * @param type
     */
    public void setType(String type) {
        Type = type;
    }

    /**
     * getStart method to get start
     * @return
     */
    public LocalDateTime getStart() {
        return Start;
    }

    /**
     * setStart method to set start
     * @param start
     */
    public void setStart(LocalDateTime start) {
        Start = start;
    }

    /**
     * getEnd method to get end
     * @return
     */
    public LocalDateTime getEnd() {
        return End;
    }

    /**
     * setEnd method to set end
     * @param end
     */
    public void setEnd(LocalDateTime end) {
        End = end;
    }

    /**
     * getCreate_Date method to get date created
     * @return
     */
    public LocalDateTime getCreate_Date() {
        return Create_Date;
    }

    /**
     * setCreate_Date to set date created
     * @param create_Date
     */
    public void setCreate_Date(LocalDateTime create_Date) {
        Create_Date = create_Date;
    }

    /**
     * getCreated_By method to get created by
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
     * getLast_Update method to get last updated
     * @return
     */
    public Timestamp getLast_Update() {
        return Last_Update;
    }

    /**
     * setLast_Update method to set last updated
     * @param last_Update
     */
    public void setLast_Update(Timestamp last_Update) {
        Last_Update = last_Update;
    }

    /**
     * getLast_updated_by method to the last updated by
     * @return
     */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /**
     * setLast_updated_by method to set last updated by
     * @param last_Updated_By
     */
    public void setLast_Updated_By(String last_Updated_By) {
        Last_Updated_By = last_Updated_By;
    }

    /**
     * getCustomer_ID method to get customer id
     * @return
     */
    public int getCustomer_ID() {
        return Customer_ID;
    }

    /**
     * setCustomer_ID method to set customer id
     * @param customer_ID
     */
    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }

    /**
     * getUser_ID method to get user id
     * @return
     */
    public int getUser_ID() {
        return User_ID;
    }

    /**
     * setUser_ID method to set user id
     * @param user_ID
     */
    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }

    /**
     * getContact_ID method to get contact id
     * @return
     */
    public int getContact_ID() {
        return Contact_ID;
    }

    /**
     * setContact_ID method to set contact id
     * @param contact_ID
     */
    public void setContact_ID(int contact_ID) {
        Contact_ID = contact_ID;
    }

    /**
     * getContact_Name method to get contact name
     * @return
     */
    public String getContact_Name() {
        return Contact_Name;
    }

    /**
     * setContact_Name method to set contact name
     * @param contact_Name
     */
    public void setContact_Name(String contact_Name) {
        Contact_Name = contact_Name;
    }

    /**
     * getCustomer_Name method to get customer name
     * @return
     */
    public String getCustomer_Name() {
        return Customer_Name;
    }

    /**
     * setCustomer_Name method to set customer name
     * @param customer_Name
     */
    public void setCustomer_Name(String customer_Name) {
        Customer_Name = customer_Name;
    }

    /**
     * getStartMonth method to get start month
     * @return
     */
    public String getStartMonth() {
        return startMonth;
    }

    /**
     * setStartMonth method to set start month
     * @param startMonth
     */
    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    /**
     * getCount method to get number of appointments
     * @return
     */
    public int getCount() {
        return Count;
    }

    /**
     * setCount method to set number of appointments
     * @param count
     */
    public void setCount(int count) {
        Count = count;
    }
}
