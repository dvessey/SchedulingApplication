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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.util.ResourceBundle;

/**
 * AddAppointmentController class controls adding appointments
 * @author Damon Vessey
 */
public class AddAppointmentController implements Initializable {
    public TextField titleField;
    public TextField descriptionField;
    public TextField locationField;
    public ComboBox<Contacts> contactCombo;
    public TextField typeField;
    public DatePicker startDatePicker;
    public ComboBox<LocalTime> startTimeComboBox;
    public ComboBox<LocalTime> endTimeComboBox;
    public TextField customerIdTextField;
    public TextField userIdTextField;

    ObservableList<LocalTime> startTime = FXCollections.observableArrayList();
    ObservableList<LocalTime> endTime = FXCollections.observableArrayList();

    private boolean isOverlap = false;
    private boolean hasErrors = false;

    /**
     * onAddAppointment method to add appointments
     * <p><b>lambda expression to check for overlapping times</b></p>
     * @param actionEvent
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public void onAddAppointment(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {

        try {
            validateBlank();
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            if (!hasErrors) {
                try {
                    isOverlap = false;
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
                    Timestamp startTs = Timestamp.valueOf(startDateTime);
                    Timestamp endTs = Timestamp.valueOf(endDateTime);

                    Customers selectedCustomer = CustomerDaoImpl.getCustomer(Integer.parseInt(customerIdTextField.getText()));
                    assert selectedCustomer != null;
                    int customerId = selectedCustomer.getCustomer_ID();

                    int userId = Integer.parseInt(userIdTextField.getText());

                    LocalDateTime createDate = LocalDateTime.now();

                    Users theUser = LoginController.getTheUser();
                    String userName = theUser.getUser_name();

                    String createdBy = userName;
                    Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());
                    String lastUpdatedBy = userName;
                    int contactID = contact.getContact_ID();

                    //to check against business hours
                    ZonedDateTime startLocal = startDateTime.atZone(ZoneId.systemDefault());
                    ZonedDateTime endLocal = endDateTime.atZone(ZoneId.systemDefault());

                    //8am to 10pm EST business hours
                    ZonedDateTime businessOpen = LocalDateTime.of(date, LocalTime.of(8, 0)).atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York"));
                    ZonedDateTime businessClosed = LocalDateTime.of(date, LocalTime.of(22, 0)).atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York"));

                    if (startLocal.isBefore(businessOpen) || endLocal.isAfter(businessClosed)) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Outside of Business Hours");
                        alert.setHeaderText("The time you've chosen is outside of business hours.");
                        alert.setContentText("Please enter a time between 8am to 10pm EST");
                        alert.showAndWait();
                    } else {
                        ObservableList<Appointments> allAppointments = AppointmentDaoImpl.getAppointments();

                        allAppointments.forEach(appointment -> {
                            LocalDateTime appointmentStart = appointment.getStart();
                            LocalDateTime appointmentEnd = appointment.getEnd();

                            //startDateTime >= appointmentStart && startDateTime < appointmentEnd
                            if ((startDateTime.isAfter(appointmentStart) || startDateTime.isEqual(appointmentStart)) && startDateTime.isBefore(appointmentEnd)) {
                                //overlap occurs
                                isOverlap = true;
                            }
                            //endDateTime > appointmentStart && endDateTime <= appointmentEnd
                            if (endDateTime.isAfter(appointmentStart) && (endDateTime.isBefore(appointmentEnd) || endDateTime.isEqual(appointmentEnd))) {
                                isOverlap = true;
                            }
                            //startDateTime <= appointmentStart && endDateTime >= appointmentEnd
                            if ((startDateTime.isBefore(appointmentStart) || startDateTime.isEqual(appointmentStart)) && (endDateTime.isAfter(appointmentEnd) || endDateTime.isEqual(appointmentEnd))) {
                                isOverlap = true;
                            }
                        });

                        if (!isOverlap && !hasErrors) {
                            AppointmentDaoImpl.addAppointment(new Appointments(title, description, theContact, location, type, startDateTime, endDateTime, createDate, createdBy, lastUpdate, lastUpdatedBy, customerId, userId, contactID));
                            goToHomeScreen(actionEvent);
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Overlapping appointments");
                            alert.setHeaderText("An appointment is already scheduled for this time.");
                            alert.setContentText("Please enter another time.");
                            alert.showAndWait();
                        }
                    }
                } catch (NullPointerException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Blank fields");
                    alert.setHeaderText("No fields can be blank");
                    alert.setContentText("Please fill out the form");
                    alert.showAndWait();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * onCancel method to go back to home screen
     * @param actionEvent
     * @throws IOException
     */
    public void onCancel(ActionEvent actionEvent) throws IOException {
        goToHomeScreen(actionEvent);
    }

    /**
     * initialize method to initialize combo box values
     * @param url
     * @param resourceBundle
     */
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

        ObservableList<Contacts> contacts = null;
        try {
            contacts = ContactDaoImpl.getContacts();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        contactCombo.setItems(contacts);

    }

    /**
     * goToHomeScreen method to go back to main screen
     * @param actionEvent
     * @throws IOException
     */
    public void goToHomeScreen(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/damon/schedulingapplication/appointments.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1105, 700);
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * validateBlank method to validate all fields are filled out
     */
    private void validateBlank() {
        if(titleField.getText().trim().isBlank() || descriptionField.getText().trim().isBlank() || typeField.getText().trim().isBlank() ||
                contactCombo.getSelectionModel().isEmpty() || customerIdTextField.getText().trim().isEmpty() || startTimeComboBox.getSelectionModel().isEmpty()
               || locationField.getText().trim().isBlank() || endTimeComboBox.getSelectionModel().isEmpty() || startDatePicker.getValue() == null
                || userIdTextField.getText().trim().isBlank()) {

            hasErrors=true;
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Blank fields");
            alert.setHeaderText("No fields can be blank");
            alert.setContentText("Please fill out the form");
            alert.showAndWait();
        } else{
            hasErrors = false;
        }

    }
}
