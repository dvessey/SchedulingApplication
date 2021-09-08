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
import javafx.scene.control.Button;
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

public class EditAppointmentController implements Initializable {
    public TextField titleField;
    public TextField descriptionField;
    public TextField locationField;
    public ComboBox<Contacts> contactCombo;
    public TextField typeField;
    public DatePicker startDatePicker;
    public ComboBox<LocalTime> startTimeComboBox;
    public ComboBox<LocalTime> endTimeComboBox;
    public Button saveAppointmentButton;
    public Button cancelButton;
    public ComboBox<Customers> customerComboBox;
    public TextField appointmentIdLabel;

    private Appointments theAppointment;
    ObservableList<LocalTime> startTime = FXCollections.observableArrayList();
    ObservableList<LocalTime> endTime = FXCollections.observableArrayList();


    public void onSaveAppointment(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        theAppointment.setTitle(titleField.getText());
        theAppointment.setDescription(descriptionField.getText());
        theAppointment.setLocation(locationField.getText());
        theAppointment.setContact_ID(contactCombo.getSelectionModel().getSelectedItem().getContact_ID());
        //Contacts contact = contactCombo.getSelectionModel().getSelectedItem();
        LocalDate date = startDatePicker.getValue();
        theAppointment.setType(typeField.getText());
        LocalTime startTime = startTimeComboBox.getSelectionModel().getSelectedItem();
        LocalTime endTime = endTimeComboBox.getSelectionModel().getSelectedItem();
        LocalDateTime startDateTime = LocalDateTime.of(date, startTime);
        theAppointment.setStart(startDateTime);
        LocalDateTime endDateTime = LocalDateTime.of(date, endTime);
        theAppointment.setEnd(endDateTime);
        //Customers selectedCustomer = customerComboBox.getSelectionModel().getSelectedItem();
        theAppointment.setCustomer_ID(customerComboBox.getSelectionModel().getSelectedItem().getCustomer_ID());
        theAppointment.setCreate_Date(theAppointment.getCreate_Date());
        theAppointment.setCreated_By(theAppointment.getCreated_By());
        theAppointment.setLast_Update(Timestamp.valueOf(LocalDateTime.now()));
        theAppointment.setLast_Updated_By(Users.getUser_name());
        theAppointment.setUser_ID(Users.getUserID());
        //int customerId = selectedCustomer.getCustomer_ID();
        //int contactID = contact.getContact_ID();
        //int userId = Users.getUserID();

        AppointmentDaoImpl.updateAppointment(theAppointment);
        goToHomeScreen(actionEvent);
    }

    public void onCancel(ActionEvent actionEvent) throws IOException {
        goToHomeScreen(actionEvent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        theAppointment = AppointmentsController.getTheAppointment();
        Integer theAppointmentId = theAppointment.getAppointment_ID();
        appointmentIdLabel.setText(theAppointmentId.toString());
        String theAppointmentTitle = theAppointment.getTitle();
        titleField.setText(theAppointmentTitle);
        String theAppointmentDescription = theAppointment.getDescription();
        descriptionField.setText(theAppointmentDescription);
        String theAppointmentLocation = theAppointment.getLocation();
        locationField.setText(theAppointmentLocation);
        Contacts theContact = null;
        try {
            theContact = ContactDaoImpl.getContact(theAppointment.getContact_ID());
            System.out.println("Edit appointment initialize - contactId: " + theAppointment.getContact_ID());
            contactCombo.setValue(theContact);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        String theAppointmentType = theAppointment.getType();
        typeField.setText(theAppointmentType);
        LocalDate theAppointmentStartDate = theAppointment.getStart().toLocalDate();
        startDatePicker.setValue(theAppointmentStartDate);
        LocalTime theAppointmentStartTime = theAppointment.getStart().toLocalTime();
        startTimeComboBox.setValue(theAppointmentStartTime);
        LocalTime theAppointmentEndTime = theAppointment.getEnd().toLocalTime();
        endTimeComboBox.setValue(theAppointmentEndTime);
        Customers theCustomer = null;
        try {
            theCustomer = CustomerDaoImpl.getCustomer(theAppointment.getCustomer_ID());
            customerComboBox.setValue(theCustomer);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        //populate combo boxes
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
            customerComboBox.setItems(customers);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


        ObservableList<Contacts> contacts = null;
        try {
            contacts = ContactDaoImpl.getContacts();
            contactCombo.setItems(contacts);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


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
