package com.damon.schedulingapplication.Controller;

import com.damon.schedulingapplication.DAO.AppointmentDaoImpl;
import com.damon.schedulingapplication.Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Month;
import java.util.*;

/**
 * ReportsController class to show all reports
 * @author Damon Vessey
 */
public class ReportsController  {
    /**
     * onCancel method to go back to home screen
     * @param actionEvent
     * @throws IOException
     */
    public void onCancel(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/damon/schedulingapplication/appointments.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1105, 700);
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * onCustomerReports method to go to customer report screen
     * @param actionEvent
     * @throws IOException
     */
    public void onCustomerReports(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/damon/schedulingapplication/customerReport.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 760);
        stage.setTitle("Customer Report");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * onContactReports method to go to contact report screen
     * @param actionEvent
     * @throws IOException
     */
    public void onContactReports(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/damon/schedulingapplication/contactReport.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 760);
        stage.setTitle("Contact Report");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * onLocationReports method to go to location reports screen
     * @param actionEvent
     * @throws IOException
     */
    public void onLocationReports(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/damon/schedulingapplication/locationReport.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 760);
        stage.setTitle("Location Report");
        stage.setScene(scene);
        stage.show();
    }
}

