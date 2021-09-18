package com.damon.schedulingapplication.Controller;

import com.damon.schedulingapplication.DAO.AppointmentDaoImpl;
import com.damon.schedulingapplication.Model.Appointments;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * CustomerReportController class to handle customer reports
 * @author Damon Vessey
 */
public class CustomerReportController implements Initializable {
    public TextArea monthlyCustomerAppointmentsTextArea;

    /**
     * initialize method to initialize customer reports
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int longest = "September".length();
        int spacingSeperation = 20;
        int spacing = longest + spacingSeperation;
        String headerFormatted = String.format("%-" + spacing + "s%-" + spacing + "s%-" + spacing + "s", "Month", "Type", "Count");
        monthlyCustomerAppointmentsTextArea.appendText(headerFormatted);
        monthlyCustomerAppointmentsTextArea.appendText("\n");


        try {
            ObservableList<Appointments> allAppointments = AppointmentDaoImpl.getAppointmentsTypeAndMonth();
            for(Appointments appointment: allAppointments){
                String startMonth = appointment.getStartMonth();
                String type = appointment.getType();
                int count = appointment.getCount();
                String formatted = String.format("%-" + spacing + "s%-" + spacing + "s%-" + spacing + "s", startMonth, type, count);
                monthlyCustomerAppointmentsTextArea.appendText(formatted);
                monthlyCustomerAppointmentsTextArea.appendText("\n");
            }
        } catch (ClassNotFoundException | SQLException e) {
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
