package com.damon.schedulingapplication.Controller;

import com.damon.schedulingapplication.DAO.AppointmentDaoImpl;
import com.damon.schedulingapplication.DAO.ContactDaoImpl;
import com.damon.schedulingapplication.Model.Appointments;
import com.damon.schedulingapplication.Model.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;


/**
 * ContactReportController class to handle contact scheduling
 * @author Damon Vessey
 */
public class ContactReportController implements Initializable {


    public TextArea contactScheduleTextArea;

    /**
     * initialize method to initialize contact schedules
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Contact Schedule
        try {
            ObservableList<Appointments> contactSchedule = FXCollections.observableArrayList();

            ObservableList<Contacts> contacts = ContactDaoImpl.getContacts();
            String formattedHeader = String.format("%-25s %-25s %-25s %-25s %-25s %-50s %-30s %-100s", "Contact", "AppointmentID", "Title", "Type", "Description", "Start", "End", "CustomerID");
            contactScheduleTextArea.appendText(formattedHeader);
            contactScheduleTextArea.appendText("\n");

            for(Contacts contact: contacts){

                int contactId = contact.getContact_ID();
                ObservableList<Appointments> contactAppointments = AppointmentDaoImpl.getAppointmentsByContact(contactId);
                String formattedCustomer = String.format("%-50s", contact.getContact_Name());
                contactScheduleTextArea.appendText(formattedCustomer);
                contactScheduleTextArea.appendText("\n");


                for(Appointments appointments: contactAppointments){
                    int appointmentId = appointments.getAppointment_ID();
                    String title = appointments.getTitle();
                    String type = appointments.getType();
                    String description = appointments.getDescription();
                    LocalDateTime start = appointments.getStart();
                    LocalDateTime end = appointments.getEnd();
                    int customerId = appointments.getCustomer_ID();

                    contactSchedule.add(new Appointments(appointmentId, title, description, type, start, end, customerId));
                    String formattedSchedule = String.format("%-50s %-25s %-25s %-25s %-25s %-25s %-30s %-100s", " ", appointmentId, title, type, description,  start, end, customerId);
                    contactScheduleTextArea.appendText(formattedSchedule);
                    contactScheduleTextArea.appendText("\n");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * onCancel method to go back to reports screen
     * @param actionEvent
     * @throws IOException
     */
    public void onCancel(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/damon/schedulingapplication/reports.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 760);
        stage.setTitle("Reports");
        stage.setScene(scene);
        stage.show();
    }
}
