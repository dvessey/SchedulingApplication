package com.damon.schedulingapplication.DAO;

import com.damon.schedulingapplication.Model.Contacts;
import com.damon.schedulingapplication.utils.DbConnection;
import com.damon.schedulingapplication.utils.DbQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ContactDaoImpl class to handle database transactions with contacts
 * @author Damon Vessey
 */
public class ContactDaoImpl {
    public static ObservableList<Contacts> getContacts() throws ClassNotFoundException, SQLException {
        Connection conn = DbConnection.startConnection();
        String selectStatement = "SELECT * FROM contacts";
        DbQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();
        preparedStatement.execute();

        ObservableList<Contacts> contacts = FXCollections.observableArrayList();

        ResultSet resultSet = preparedStatement.getResultSet();

        while (resultSet.next()){
            int contactId = resultSet.getInt("Contact_ID");
            String contactName = resultSet.getString("Contact_Name");

            contacts.add(new Contacts(contactId, contactName));
        }
        DbConnection.closeConnection();
        return contacts;

    }

    /**
     * getContact method to retrieve contact by contact Id
     * @param contactId
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Contacts getContact(int contactId) throws ClassNotFoundException, SQLException {
        Connection conn = DbConnection.startConnection();
        String selectStatement = "SELECT * FROM contacts WHERE Contact_ID = '" + contactId + "'";
        DbQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();
        preparedStatement.execute();

        ResultSet resultSet = preparedStatement.getResultSet();
        while (resultSet.next()){
            int theContactId = resultSet.getInt("Contact_ID");
            String contactName = resultSet.getString("Contact_Name");

            Contacts theContact = new Contacts(theContactId, contactName);
            DbConnection.closeConnection();
            return theContact;
        }
       return null;
    }
}
