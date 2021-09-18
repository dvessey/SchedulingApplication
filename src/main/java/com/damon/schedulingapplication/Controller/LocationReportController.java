package com.damon.schedulingapplication.Controller;

import com.damon.schedulingapplication.DAO.AppointmentDaoImpl;
import com.damon.schedulingapplication.Model.Appointments;
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
import java.util.ResourceBundle;

/**
 * LocationReportController class to show the number of appointments by location
 * @author Damon Vessey
 */
public class LocationReportController implements Initializable {
    public TextArea appointmentsLocationTextArea;

    /**
     * initialize method to load all appointments by location
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<Appointments> locationTotal = FXCollections.observableArrayList();

            ObservableList<Appointments> locations = AppointmentDaoImpl.getAppointmentsByLocation();

            String formattedHeader = String.format("%-50s %-100s", "Location", "Total Number of Appointments");
            appointmentsLocationTextArea.appendText(formattedHeader);
            appointmentsLocationTextArea.appendText("\n");
            for(Appointments location: locations){
                String appointmentLocation = location.getLocation();
                int count = location.getCount();

                locationTotal.add(new Appointments(appointmentLocation, count));
                String formatted = String.format("%-50s %-100s", appointmentLocation, count);
                appointmentsLocationTextArea.appendText(formatted);
                appointmentsLocationTextArea.appendText("\n");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    /**
     * onCancel method to go back to report screen
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
