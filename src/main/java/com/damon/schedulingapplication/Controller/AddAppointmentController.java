package com.damon.schedulingapplication.Controller;

import com.damon.schedulingapplication.DAO.AppointmentDaoImpl;
import com.damon.schedulingapplication.DAO.ContactDaoImpl;
import com.damon.schedulingapplication.DAO.CustomerDaoImpl;
import com.damon.schedulingapplication.Model.Appointments;
import com.damon.schedulingapplication.Model.Contacts;
import com.damon.schedulingapplication.Model.Customers;
import com.damon.schedulingapplication.Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {
    public TextField titleField;
    public TextField descriptionField;
    public TextField locationField;
    public ComboBox<Contacts> contactCombo;
    public TextField typeField;
    public DatePicker startDatePicker;
    public ComboBox<LocalTime> startTimeComboBox;
    public ComboBox<LocalTime> endTimeComboBox;
    public ComboBox<Customers> customerComboBox;

    ObservableList<LocalTime> startTime = FXCollections.observableArrayList();
    ObservableList<LocalTime> endTime = FXCollections.observableArrayList();


    public void onAddAppointment(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        String title = titleField.getText();
        String description = descriptionField.getText();
        String location = locationField.getText();
        Contacts contact = contactCombo.getSelectionModel().getSelectedItem();
        String theContact = contact.getContact_Name();
        LocalDate date = startDatePicker.getValue();
        String type = typeField.getText();
        LocalTime startTime = startTimeComboBox.getSelectionModel().getSelectedItem();
        LocalTime endTime = endTimeComboBox.getSelectionModel().getSelectedItem();
        LocalDateTime startDateTime = LocalDateTime.of(date, startTime);
        LocalDateTime endDateTime = LocalDateTime.of(date, endTime);
        Customers selectedCustomer = customerComboBox.getSelectionModel().getSelectedItem();

        LocalDateTime createDate = LocalDateTime.now();
        String createdBy = Users.getUser_name();
        Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());
        String lastUpdatedBy = Users.getUser_name();
        int customerId = selectedCustomer.getCustomer_ID();
        int contactID = contact.getContact_ID();
        int userId = Users.getUserID();


        AppointmentDaoImpl.addAppointment(new Appointments(title, description, location, theContact, type, startDateTime, endDateTime, createDate, createdBy, lastUpdate, lastUpdatedBy, customerId, userId, contactID));
        goToHomeScreen(actionEvent);
    }

    public void onCancel(ActionEvent actionEvent) throws IOException {
        goToHomeScreen(actionEvent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalTime time = LocalTime.of(0, 0);
        while (!time.equals(LocalTime.of(23, 30))) {
            startTime.add(time);
            endTime.add(time);
            time = time.plusMinutes(30);
        }
        startTimeComboBox.setItems(startTime);
        endTimeComboBox.setItems(endTime);

        ObservableList<Customers> customers = null;
        try {
            customers = CustomerDaoImpl.getCustomers();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        customerComboBox.setItems(customers);

        ObservableList<Contacts> contacts = null;
        try {
            contacts = ContactDaoImpl.getContacts();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        contactCombo.setItems(contacts);

    }

    public void goToHomeScreen(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/damon/schedulingapplication/appointments.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
    }
}
