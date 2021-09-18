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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.util.ResourceBundle;

/**
 * EditAppointmentController class to handle editing appointments
 * @author Damon Vessey
 */
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
    //public ComboBox<Customers> customerComboBox;
    public TextField appointmentIdLabel;
    public TextField customerIdTextField;
    public TextField userIdTextField;

    private boolean hasErrors = false;

    private Appointments theAppointment;
    private boolean isOverlap = false;
    ObservableList<LocalTime> startTime = FXCollections.observableArrayList();
    ObservableList<LocalTime> endTime = FXCollections.observableArrayList();

    /**
     * onSaveAppointment method to save the appointment
     * <p><b>lambda expression to check for conflicting time overlaps</b></p>
     * @param actionEvent
     * @throws IOException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void onSaveAppointment(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {

        try {
            validateBlank();
        } catch (Exception e) {
            System.out.println(e);
        }

       // try {
            if(!hasErrors) {
                theAppointment.setTitle(titleField.getText());
                theAppointment.setDescription(descriptionField.getText());
                theAppointment.setLocation(locationField.getText());
                theAppointment.setContact_ID(contactCombo.getSelectionModel().getSelectedItem().getContact_ID());
                LocalDate date = startDatePicker.getValue();
                theAppointment.setType(typeField.getText());
                LocalTime startTime = startTimeComboBox.getSelectionModel().getSelectedItem();
                LocalTime endTime = endTimeComboBox.getSelectionModel().getSelectedItem();
                LocalDateTime startDateTime = LocalDateTime.of(date, startTime);
                //Timestamp startTs = Timestamp.valueOf(startDateTime);
                theAppointment.setStart(startDateTime);
                LocalDateTime endDateTime = LocalDateTime.of(date, endTime);
                //Timestamp endTs = Timestamp.valueOf(endDateTime);
                theAppointment.setEnd(endDateTime);
                theAppointment.setCustomer_ID(Integer.parseInt(customerIdTextField.getText()));
                theAppointment.setCreate_Date(theAppointment.getCreate_Date());
                theAppointment.setCreated_By(theAppointment.getCreated_By());
                theAppointment.setLast_Update(Timestamp.valueOf(LocalDateTime.now()));
                Users theUser = LoginController.getTheUser();
                String userName = theUser.getUser_name();
                theAppointment.setLast_Updated_By(userName);
                theAppointment.setUser_ID(Integer.parseInt(userIdTextField.getText()));

                //GET ALL APPOINTMENTS EXCEPT THIS APPOINTMENT ID TO CHECK CONFLICTING TIMES IN DATABASE
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
                    ObservableList<Appointments> allAppointments = AppointmentDaoImpl.getAppointmentsExludingAppoinment(theAppointment.getAppointment_ID());

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
                        AppointmentDaoImpl.updateAppointment(theAppointment);
                        goToHomeScreen(actionEvent);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Overlapping appointments");
                        alert.setHeaderText("An appointment is already scheduled for this time.");
                        alert.setContentText("Please enter another time.");
                        alert.showAndWait();
                    }
                }
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
     * initialize method to load all appointment data
     * @param url
     * @param resourceBundle
     */
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
            contactCombo.setValue(theContact);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        String theAppointmentType = theAppointment.getType();
        typeField.setText(theAppointmentType);
        LocalDateTime theAppointmentStartDate = theAppointment.getStart();
        startDatePicker.setValue(theAppointmentStartDate.toLocalDate());
        LocalDateTime theAppointmentStartTime = theAppointment.getStart();
        startTimeComboBox.setValue(theAppointmentStartTime.toLocalTime());
        LocalDateTime theAppointmentEndTime = theAppointment.getEnd();
        endTimeComboBox.setValue(theAppointmentEndTime.toLocalTime());

        int customerId = theAppointment.getCustomer_ID();
        customerIdTextField.setText(Integer.toString(customerId));

        int userId = theAppointment.getUser_ID();
        userIdTextField.setText(Integer.toString(userId));

        //populate combo boxes
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
            contactCombo.setItems(contacts);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * goToHomeScreen method to go back to the home screen
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
     * validateBlank method to ensure all fields are filled out
     */
    private void validateBlank() {
           if(titleField.getText().trim().isBlank() || titleField.getLength() == 0){
               hasErrors=true;
               System.out.println("Title");
           }
            if(descriptionField.getText().trim().isBlank() || descriptionField.getLength() == 0){
                hasErrors=true;
                System.out.println("Desc");
            }
           if(typeField.getText().trim().isBlank() || typeField.getLength() == 0){
                hasErrors=true;
               System.out.println("Type");
            }
           if(locationField.getText().trim().isBlank() || locationField.getLength() == 0){
               hasErrors=true;
               System.out.println("Location");
           }
           if(contactCombo.getSelectionModel().getSelectedItem() == null ){
               hasErrors = true;
               System.out.println("Contact");
           }
            if(customerIdTextField.getText().isBlank() || customerIdTextField.getLength() == 0){
                hasErrors = true;
                System.out.println("Customer");
            }
            if(userIdTextField.getText().isBlank() || userIdTextField.getLength() == 0){
                hasErrors = true;
                System.out.println("User");
            }
            if(startTimeComboBox.getSelectionModel().isEmpty()){
                hasErrors = true;
                System.out.println("Start");
            }
            if(endTimeComboBox.getSelectionModel().isEmpty()){
                hasErrors = true;
                System.out.println("End");
            }
            if(startDatePicker.getValue() == null){
                hasErrors = true;
                System.out.println("Datepicker");
            }

        if(hasErrors) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Blank fields");
            alert.setHeaderText("No fields can be blank");
            alert.setContentText("Please fill out the form");
            alert.showAndWait();
        }
    }
}
