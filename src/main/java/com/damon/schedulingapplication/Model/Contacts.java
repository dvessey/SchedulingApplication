package com.damon.schedulingapplication.Model;

/**
 * Contacts class
 * @author Damon Vessey
 */
public class Contacts {
    private int Contact_ID;
    private String Contact_Name;
    private String Email;

    /**
     * Contacts constructor
     * @param contact_ID
     * @param contact_Name
     * @param email
     */
    public Contacts(int contact_ID, String contact_Name, String email) {
        Contact_ID = contact_ID;
        Contact_Name = contact_Name;
        Email = email;
    }

    /**
     * Contacts constructor
     * @param contactId
     * @param contactName
     */
    public Contacts(int contactId, String contactName) {
        Contact_ID = contactId;
        Contact_Name = contactName;
    }

    /**
     * getContact_ID method to get contact ID
     * @return
     */
    public int getContact_ID() {
        return Contact_ID;
    }

    /**
     * setContact_ID method to set contact ID
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
     * getEmail method to get email
     * @return
     */
    public String getEmail() {
        return Email;
    }

    /**
     * setEmail method to set email
     * @param email
     */
    public void setEmail(String email) {
        Email = email;
    }

    /**
     * toString method to print object as customer name
     * @return
     */
    @Override
    public String toString(){
        return Contact_Name;
    }
}
