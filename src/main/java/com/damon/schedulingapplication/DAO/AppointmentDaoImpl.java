package com.damon.schedulingapplication.DAO;

import com.damon.schedulingapplication.Model.Appointments;
import com.damon.schedulingapplication.utils.DbConnection;
import com.damon.schedulingapplication.utils.DbQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.*;

/**
 * AppointmentDaoImpl class to handle all database transactions concerning appointments
 * @author Damon Vessey
 */
public class AppointmentDaoImpl {

    public static ObservableList<Appointments> getAppointments(int theCustomerId) throws ClassNotFoundException, SQLException {
        Connection conn = DbConnection.startConnection();
        String selectStatement = "SELECT Appointment_ID, Title, Description, Location, app.Create_Date, app.Created_By, app.Last_Update, app.Last_Updated_By, con.Contact_Name, app.Type, app.Start, app.End, cus.Customer_ID, app.User_ID, con.Contact_ID " +
                "FROM appointments app " +
                "INNER JOIN contacts con on con.Contact_ID = app.Contact_ID " +
                "LEFT JOIN customers cus on cus.Customer_ID = app.Customer_ID " +
                "WHERE app.Customer_ID = " + theCustomerId +
                " ORDER BY Start";

        DbQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();
        preparedStatement.execute();
        ObservableList<Appointments> appointmentsObservableList = FXCollections.observableArrayList();

        ResultSet resultSet = preparedStatement.getResultSet();

        while (resultSet.next()) {
            int appointmentId = resultSet.getInt("Appointment_ID");
            String title = resultSet.getString("Title");
            String description = resultSet.getString("Description");
            String location = resultSet.getString("Location");
            String contact = resultSet.getString("Contact_Name");
            String type = resultSet.getString("Type");
            LocalDateTime createDate = resultSet.getObject("Create_Date", LocalDateTime.class);
            String created_by = resultSet.getString("Created_By");
            int customerId = resultSet.getInt("Customer_ID");
            int userId = resultSet.getInt("User_ID");
            int contactId = resultSet.getInt("Contact_ID");
            Timestamp lastUpdate = resultSet.getTimestamp("Last_Update");
            String lastUpdatedBy = resultSet.getString("Last_Updated_By");
            LocalDateTime startTs = resultSet.getTimestamp("Start").toLocalDateTime();
            LocalDateTime endTs = resultSet.getTimestamp("End").toLocalDateTime();


            appointmentsObservableList.add(new Appointments(appointmentId, title, description, contact, location, type, startTs, endTs, createDate, created_by, lastUpdate, lastUpdatedBy, customerId, userId, contactId));
        }
        DbConnection.closeConnection();
        return appointmentsObservableList;
    }

    /**
     * getAppointmentsByContact method to retrieve appointments by contactID
     * @param contactId
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static ObservableList<Appointments> getAppointmentsByContact(int contactId) throws ClassNotFoundException, SQLException {
        Connection conn = DbConnection.startConnection();
        String selectStatement = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID " +
                "FROM appointments " +
                "WHERE  Contact_ID = " + contactId +
                " ORDER BY Start";

        DbQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();
        preparedStatement.execute();
        ObservableList<Appointments> appointmentsObservableList = FXCollections.observableArrayList();

        ResultSet resultSet = preparedStatement.getResultSet();

        while (resultSet.next()) {
            int appointmentId = resultSet.getInt("Appointment_ID");
            String title = resultSet.getString("Title");
            String description = resultSet.getString("Description");
            String type = resultSet.getString("Type");
            int customerId = resultSet.getInt("Customer_ID");
            LocalDateTime startTs = resultSet.getTimestamp("Start").toLocalDateTime();
            LocalDateTime endTs = resultSet.getTimestamp("End").toLocalDateTime();

            appointmentsObservableList.add(new Appointments(appointmentId, title, description, type, startTs, endTs, customerId));
        }
        DbConnection.closeConnection();
        return appointmentsObservableList;
    }

    public static ObservableList<Appointments> getAppointmentsByLocation() throws ClassNotFoundException, SQLException {
        Connection conn = DbConnection.startConnection();
        String selectStatement = "SELECT Location, Count(*) as Total " +
                "FROM appointments " +
                "GROUP BY Location " +
                "ORDER BY Location";

        DbQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();
        preparedStatement.execute();
        ObservableList<Appointments> appointmentsObservableList = FXCollections.observableArrayList();

        ResultSet resultSet = preparedStatement.getResultSet();

        while (resultSet.next()) {
            String location = resultSet.getString("Location");
            int count = resultSet.getInt("Total");

            appointmentsObservableList.add(new Appointments(location, count));
        }
        DbConnection.closeConnection();
        return appointmentsObservableList;
    }

    /**
     * getAppointments method to retrieve all appointments
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static ObservableList<Appointments> getAppointments() throws SQLException, ClassNotFoundException {
        Connection conn = DbConnection.startConnection();
        String selectStatement = "SELECT Appointment_ID, Title, Description, Location, app.Create_Date, app.Created_By, app.Last_Update, app.Last_Updated_By, con.Contact_Name, app.Type, app.Start, app.End, cus.Customer_ID, app.User_ID, con.Contact_ID " +
                "FROM appointments app " +
                "INNER JOIN contacts con on con.Contact_ID = app.Contact_ID " +
                "LEFT JOIN customers cus on cus.Customer_ID = app.Customer_ID " +
                "ORDER BY Start";

        DbQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();
        preparedStatement.execute();

        ObservableList<Appointments> appointmentsObservableList = FXCollections.observableArrayList();

        ResultSet resultSet = preparedStatement.getResultSet();

        while (resultSet.next()) {
            int appointmentId = resultSet.getInt("Appointment_ID");
            String title = resultSet.getString("Title");
            String description = resultSet.getString("Description");
            String location = resultSet.getString("Location");
            String contact = resultSet.getString("Contact_Name");
            String type = resultSet.getString("Type");
            LocalDateTime startTs = resultSet.getTimestamp("Start").toLocalDateTime();
            LocalDateTime endTs = resultSet.getTimestamp("End").toLocalDateTime();
            LocalDateTime createDate = resultSet.getObject("Create_Date", LocalDateTime.class);
            String created_by = resultSet.getString("Created_By");
            int customerId = resultSet.getInt("Customer_ID");
            int userId = resultSet.getInt("User_ID");
            int contactId = resultSet.getInt("Contact_ID");
            Timestamp lastUpdate = resultSet.getTimestamp("Last_Update");
            String lastUpdatedBy = resultSet.getString("Last_Updated_By");

            appointmentsObservableList.add(new Appointments(appointmentId, title, description, contact, location, type, startTs, endTs, createDate, created_by, lastUpdate, lastUpdatedBy, customerId, userId, contactId));
        }
        DbConnection.closeConnection();
        return appointmentsObservableList;
    }

    /**
     * getAppointmentsExcludingAppointment method to retrieve all appointments except for the one being edited
     * @param excludedAppointmentId
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static ObservableList<Appointments> getAppointmentsExludingAppoinment(int excludedAppointmentId) throws SQLException, ClassNotFoundException {
        Connection conn = DbConnection.startConnection();
        String selectStatement = "SELECT Appointment_ID, Title, Description, Location, app.Create_Date, app.Created_By, app.Last_Update, app.Last_Updated_By, con.Contact_Name, app.Type, app.Start, app.End, cus.Customer_ID, app.User_ID, con.Contact_ID " +
                "FROM appointments app " +
                "INNER JOIN contacts con on con.Contact_ID = app.Contact_ID " +
                "LEFT JOIN customers cus on cus.Customer_ID = app.Customer_ID " +
                "WHERE NOT Appointment_ID = " + excludedAppointmentId +
                " ORDER BY Start";

        DbQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();
        preparedStatement.execute();

        ObservableList<Appointments> appointmentsObservableList = FXCollections.observableArrayList();

        ResultSet resultSet = preparedStatement.getResultSet();

        while (resultSet.next()) {
            int appointmentId = resultSet.getInt("Appointment_ID");
            String title = resultSet.getString("Title");
            String description = resultSet.getString("Description");
            String location = resultSet.getString("Location");
            String contact = resultSet.getString("Contact_Name");
            String type = resultSet.getString("Type");
            LocalDateTime startTs = resultSet.getTimestamp("Start").toLocalDateTime();
            LocalDateTime endTs = resultSet.getTimestamp("End").toLocalDateTime();
            LocalDateTime createDate = resultSet.getObject("Create_Date", LocalDateTime.class);
            String created_by = resultSet.getString("Created_By");
            int customerId = resultSet.getInt("Customer_ID");
            int userId = resultSet.getInt("User_ID");
            int contactId = resultSet.getInt("Contact_ID");
            Timestamp lastUpdate = resultSet.getTimestamp("Last_Update");
            String lastUpdatedBy = resultSet.getString("Last_Updated_By");

            appointmentsObservableList.add(new Appointments(appointmentId, title, description, location, contact, type, startTs, endTs, createDate, created_by, lastUpdate, lastUpdatedBy, customerId, userId, contactId));
        }
        DbConnection.closeConnection();
        return appointmentsObservableList;
    }

    /**
     * getUserAppointments method to get appointments based on the user logged in
     * @param loggedInUser
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static ObservableList<Appointments> getUserAppointments(int loggedInUser) throws ClassNotFoundException, SQLException {
        Connection conn = DbConnection.startConnection();
        String selectStatement = "SELECT Appointment_ID, Title, Description, Location, app.Create_Date, app.Created_By, app.Last_Update, app.Last_Updated_By, con.Contact_Name, app.Type, app.Start, app.End, cus.Customer_ID, app.User_ID, con.Contact_ID " +
                "FROM appointments app " +
                "INNER JOIN contacts con on con.Contact_ID = app.Contact_ID " +
                "LEFT JOIN customers cus on cus.Customer_ID = app.Customer_ID " +
                "WHERE app.User_ID = " + loggedInUser +
                " ORDER BY Start";

        DbQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();
        preparedStatement.execute();
        ObservableList<Appointments> appointmentsObservableList = FXCollections.observableArrayList();

        ResultSet resultSet = preparedStatement.getResultSet();

        while (resultSet.next()) {
            int appointmentId = resultSet.getInt("Appointment_ID");
            String title = resultSet.getString("Title");
            String description = resultSet.getString("Description");
            String location = resultSet.getString("Location");
            String contact = resultSet.getString("Contact_Name");
            String type = resultSet.getString("Type");
            LocalDateTime createDate = resultSet.getObject("Create_Date", LocalDateTime.class);
            String created_by = resultSet.getString("Created_By");
            int customerId = resultSet.getInt("Customer_ID");
            int userId = resultSet.getInt("User_ID");
            int contactId = resultSet.getInt("Contact_ID");
            Timestamp lastUpdate = resultSet.getTimestamp("Last_Update");
            String lastUpdatedBy = resultSet.getString("Last_Updated_By");
            LocalDateTime startTs = resultSet.getTimestamp("Start").toLocalDateTime();
            LocalDateTime endTs = resultSet.getTimestamp("End").toLocalDateTime();

            appointmentsObservableList.add(new Appointments(appointmentId, title, description, contact, location, type, startTs, endTs, createDate, created_by, lastUpdate, lastUpdatedBy, customerId, userId, contactId));
        }
        DbConnection.closeConnection();
        return appointmentsObservableList;
    }

    /**
     * addAppointment method to save appointment to database
     * @param appointment
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void addAppointment(Appointments appointment) throws ClassNotFoundException, SQLException {
        Connection conn = DbConnection.startConnection();
        String insertStatement = "INSERT INTO appointments(Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        DbQuery.setPreparedStatement(conn, insertStatement);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();

        String title = appointment.getTitle();
        String description = appointment.getDescription();
        String location = appointment.getLocation();
        String type = appointment.getType();
        LocalDateTime start = appointment.getStart();
        LocalDateTime end = appointment.getEnd();
        Timestamp startTs = Timestamp.valueOf(start);
        Timestamp endTs = Timestamp.valueOf(end);
        LocalDateTime createDate = appointment.getCreate_Date();
        String createdBy = appointment.getCreated_By();
        Timestamp lastUpdate = appointment.getLast_Update();
        String lastUpdateBy = appointment.getLast_Updated_By();
        int customerId = appointment.getCustomer_ID();
        int userId = appointment.getUser_ID();
        int contactId = appointment.getContact_ID();

        preparedStatement.setString(1, title);
        preparedStatement.setString(2, description);
        preparedStatement.setString(3, location);
        preparedStatement.setString(4, type);
        //preparedStatement.setObject(5, start);
        //preparedStatement.setObject(6, end);
        preparedStatement.setTimestamp(5, startTs);
        preparedStatement.setTimestamp(6, endTs);
        preparedStatement.setObject(7, createDate);
        preparedStatement.setString(8, createdBy);
        preparedStatement.setObject(9, lastUpdate);
        preparedStatement.setString(10, lastUpdateBy);
        preparedStatement.setInt(11, customerId);
        preparedStatement.setInt(12, userId);
        preparedStatement.setInt(13, contactId);

        preparedStatement.execute();

        DbConnection.closeConnection();

    }

    /**
     * updateAppointment method to update appointment
     * @param appointment
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void updateAppointment(Appointments appointment) throws ClassNotFoundException, SQLException {
        Connection conn = DbConnection.startConnection();
        String updateStatement = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Create_Date = ?, Created_By = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? " +
                "WHERE Appointment_ID = " + appointment.getAppointment_ID();

        DbQuery.setPreparedStatement(conn, updateStatement);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();

        preparedStatement.setString(1, appointment.getTitle());
        preparedStatement.setString(2, appointment.getDescription());
        preparedStatement.setString(3, appointment.getLocation());
        preparedStatement.setString(4, appointment.getType());
        preparedStatement.setTimestamp(5, Timestamp.valueOf(appointment.getStart()));
        preparedStatement.setTimestamp(6, Timestamp.valueOf(appointment.getEnd()));
        preparedStatement.setObject(7, appointment.getCreate_Date());
        preparedStatement.setString(8, appointment.getCreated_By());
        preparedStatement.setObject(9, appointment.getLast_Update());
        preparedStatement.setString(10, appointment.getLast_Updated_By());
        preparedStatement.setInt(11, appointment.getCustomer_ID());
        preparedStatement.setInt(12, appointment.getUser_ID());
        preparedStatement.setInt(13, appointment.getContact_ID());

        preparedStatement.executeUpdate();
        DbConnection.closeConnection();

    }

    /**
     * deleteAppointment to delete appointment
     * @param appointmentId
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void deleteAppointment(int appointmentId) throws ClassNotFoundException, SQLException {
        Connection conn = DbConnection.startConnection();
        String deleteStatement = "DELETE FROM appointments WHERE Appointment_ID = " + appointmentId;

        DbQuery.setPreparedStatement(conn, deleteStatement);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();
        preparedStatement.executeUpdate();
        DbConnection.closeConnection();

    }

    public static ObservableList<Appointments> getAppointmentsTypeAndMonth() throws ClassNotFoundException, SQLException {
        Connection conn = DbConnection.startConnection();

        ObservableList<Appointments> appointmentsObservableList = FXCollections.observableArrayList();

        String selectStatement = "Select MONTHNAME(Start) as Month, Type, Count(*) as Total from appointments group by type, Month ORDER BY Month";

        DbQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();

        while (resultSet.next()) {
            String start = resultSet.getString("Month");
            String type = resultSet.getString("Type");
            int count = resultSet.getInt("Total");
            appointmentsObservableList.add(new Appointments(start, type, count));
        }
        DbConnection.closeConnection();
        return appointmentsObservableList;

    }
}
