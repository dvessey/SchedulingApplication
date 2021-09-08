package com.damon.schedulingapplication.DAO;

import com.damon.schedulingapplication.Model.Appointments;
import com.damon.schedulingapplication.Model.Users;
import com.damon.schedulingapplication.utils.DbConnection;
import com.damon.schedulingapplication.utils.DbQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;



public class AppointmentDaoImpl {
    private final static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public static ObservableList<Appointments> getAppointments(int theCustomerId) throws ClassNotFoundException, SQLException {
        Connection conn = DbConnection.startConnection();
        //String selectStatement = "SELECT * FROM appointments, contacts WHERE Customer_ID = " + theCustomerId;
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

        while (resultSet.next()){
            int appointmentId = resultSet.getInt("Appointment_ID");
            String title = resultSet.getString("Title");
            String description = resultSet.getString("Description");
            String location = resultSet.getString("Location");
            String contact = resultSet.getString("Contact_Name");
            String type = resultSet.getString("Type");
            LocalDate startDate = resultSet.getDate("Start").toLocalDate();
            LocalTime startTime = resultSet.getTime("Start").toLocalTime();
            LocalDate endDate = resultSet.getDate("End").toLocalDate();
            LocalTime endTime = resultSet.getTime("End").toLocalTime();
            LocalDateTime createDate = resultSet.getObject("Create_Date", LocalDateTime.class);
            String created_by = resultSet.getString("Created_By");
            int customerId = resultSet.getInt("Customer_ID");
            int userId = Users.getUserID();
            int contactId = resultSet.getInt("Contact_ID");
            System.out.println("Userid in AppointmentDaoImpl: " + userId);
            Timestamp lastUpdate = resultSet.getTimestamp("Last_Update");
            String lastUpdatedBy = resultSet.getString("Last_Updated_By");

            LocalDateTime localDateTimeStart = LocalDateTime.of(startDate, startTime);
            LocalDateTime localDateTimeEnd = LocalDateTime.of(endDate, endTime);

            appointmentsObservableList.add(new Appointments(appointmentId, title, description, contact, location, type, localDateTimeStart, localDateTimeEnd, createDate, created_by, lastUpdate, lastUpdatedBy, customerId, userId, contactId));
            System.out.println("USERID: " + Users.getUserID());


        }
        DbConnection.closeConnection();
        return appointmentsObservableList;
    }

    public static ObservableList<Appointments> getAppointments() throws SQLException, ClassNotFoundException {
        Connection conn = DbConnection.startConnection();
//        String selectStatement = "SELECT Appointment_ID, Title, Description, Location, con.Contact_Name, app.Type, app.Start, app.End, cus.Customer_ID, app.User_ID, con.Contact_ID " +
//                "FROM appointments app " +
//                "INNER JOIN contacts con on con.Contact_ID = app.Contact_ID " +
//                "LEFT JOIN customers cus on cus.Customer_ID = app.Customer_ID " +
//               // "WHERE app.User_ID = '" + Users.getUserID() + "' " +
//                "ORDER BY Start";
        String selectStatement = "SELECT Appointment_ID, Title, Description, Location, app.Create_Date, app.Created_By, app.Last_Update, app.Last_Updated_By, con.Contact_Name, app.Type, app.Start, app.End, cus.Customer_ID, app.User_ID, con.Contact_ID " +
                "FROM appointments app " +
                "INNER JOIN contacts con on con.Contact_ID = app.Contact_ID " +
                "LEFT JOIN customers cus on cus.Customer_ID = app.Customer_ID " +
                // "WHERE app.User_ID = '" + Users.getUserID() + "' " +
                "ORDER BY Start";


        System.out.println("userID in appointmentDaoImpl sql statement: " + Users.getUserID());

        DbQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();
        preparedStatement.execute();

        //Appointments appointmentResult;
        ObservableList<Appointments> appointmentsObservableList = FXCollections.observableArrayList();

        ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()){
                int appointmentId = resultSet.getInt("Appointment_ID");
                String title = resultSet.getString("Title");
                String description = resultSet.getString("Description");
                String location = resultSet.getString("Location");
                String contact = resultSet.getString("Contact_Name");
                String type = resultSet.getString("Type");
                LocalDate startDate = resultSet.getDate("Start").toLocalDate();
                LocalTime startTime = resultSet.getTime("Start").toLocalTime();
                LocalDate endDate = resultSet.getDate("End").toLocalDate();
                LocalTime endTime = resultSet.getTime("End").toLocalTime();
                LocalDateTime createDate = resultSet.getObject("Create_Date", LocalDateTime.class);
                String created_by = resultSet.getString("Created_By");
                int customerId = resultSet.getInt("Customer_ID");
                //int userId = resultSet.getInt("User_ID");
                int userId = Users.getUserID();
                int contactId = resultSet.getInt("Contact_ID");
                System.out.println("Userid in AppointmentDaoImpl: " + userId);
                Timestamp lastUpdate = resultSet.getTimestamp("Last_Update");
                String lastUpdatedBy = resultSet.getString("Last_Updated_By");

                LocalDateTime localDateTimeStart = LocalDateTime.of(startDate, startTime);
                LocalDateTime localDateTimeEnd = LocalDateTime.of(endDate, endTime);

                //appointmentsObservableList.add(new Appointments(appointmentId, title, description, location, contact, type, localDateTimeStart, localDateTimeEnd, customerId, userId, contactId));
                appointmentsObservableList.add(new Appointments(appointmentId, title, description, location, contact, type, localDateTimeStart, localDateTimeEnd, createDate, created_by, lastUpdate, lastUpdatedBy, customerId, userId, contactId));
                System.out.println("USERID: " + Users.getUserID());

                //return appointmentResult;
                System.out.println("List size in AppointmentDaoImpl: " + appointmentsObservableList.size());
            }
            DbConnection.closeConnection();
            return appointmentsObservableList;
    }

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
        preparedStatement.setObject(5, start);
        preparedStatement.setObject(6, end);
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
        preparedStatement.setObject(5, appointment.getStart());
        preparedStatement.setObject(6, appointment.getEnd());
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

    public static void deleteAppointment(int appointmentId) throws ClassNotFoundException, SQLException {
        Connection conn = DbConnection.startConnection();
        String deleteStatement = "DELETE FROM appointments WHERE Appointment_ID = " + appointmentId;

        DbQuery.setPreparedStatement(conn, deleteStatement);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();
        preparedStatement.executeUpdate();
        DbConnection.closeConnection();

    }
}
