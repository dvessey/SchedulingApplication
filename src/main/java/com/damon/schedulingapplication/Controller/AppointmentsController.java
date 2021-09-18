package com.damon.schedulingapplication.Controller;

import com.damon.schedulingapplication.DAO.AppointmentDaoImpl;
import com.damon.schedulingapplication.Model.Appointments;
import com.damon.schedulingapplication.utils.DbConnection;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * AppointmentsController class to view main screen
 * @author Damon Vessey
 */
public class AppointmentsController implements Initializable {
    public AnchorPane anchorPane;
    public TableView<Appointments> appointmentTableView;
    public TableColumn<Appointments, Integer> appointmentIdCol;
    public TableColumn<Appointments, String> titleCol;
    public TableColumn<Appointments, String> descriptionCol;
    public TableColumn<Appointments, String> locationCol;
    public TableColumn<Appointments, String> contactCol;
    public TableColumn<Appointments, String> typeCol;
    public TableColumn<Appointments, LocalDateTime> startCol;
    public TableColumn<Appointments, LocalDateTime>  endCol;
    public TableColumn<Appointments, Integer> customerIdCol;
    public RadioButton monthView;
    public ToggleGroup CalenderView;
    public RadioButton weekView;
    public Button showCustomerButton;
    public Button addAppointmentButton;
    public Button editAppointmentButton;
    public Button deleteAppointmentButton;
    public Label titleLabel;
    public Button exitButton;
    public RadioButton allRadioButton;

    private static Appointments theAppointment = null;
    public Button generateReports;
    public TableColumn<Appointments, Integer> userIdCol;

    /**
     * getTheAppointment method to pass the appointment between controllers
     * @return
     */
    public static Appointments getTheAppointment() {
        return theAppointment;
    }

    /**
     * onMonthView method to filter appointments for the current month only
     * @param actionEvent
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void onMonthView(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ObservableList<Appointments> appointments = AppointmentDaoImpl.getAppointments();
        filterByMonth(appointments);
    }

    /**
     * onWeekView method to filter appointments for the current week only
     * @param actionEvent
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void onWeekView(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ObservableList<Appointments> appointments = AppointmentDaoImpl.getAppointments();
        filterByWeek(appointments);
    }

    /**
     * onShowCustomer method to go to customer screen
     * @param actionEvent
     * @throws IOException
     */
    public void onShowCustomer(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/damon/schedulingapplication/customer.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("Customer");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * onAddAppointment method to add an appointment
     * @param actionEvent
     * @throws IOException
     */
    public void onAddAppointment(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/damon/schedulingapplication/addAppointment.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("Appointment");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * onEditApoointment method to edit the selected appointment
     * @param actionEvent
     * @throws IOException
     */
    public void onEditAppointment(ActionEvent actionEvent) throws IOException {
            if(appointmentTableView.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Appointment Selected");
                alert.setHeaderText("No appointment selected");
                alert.setContentText("Please select an appointment to edit");
                alert.showAndWait();
            } else {
                theAppointment = appointmentTableView.getSelectionModel().getSelectedItem();
                Parent root = FXMLLoader.load(getClass().getResource("/com/damon/schedulingapplication/editAppointment.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 1000, 700);
                stage.setTitle("Appointment");
                stage.setScene(scene);
                stage.show();
            }
    }

    /**
     * onDeleteAppointment method to delete the selected appointment
     * @param actionEvent
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void onDeleteAppointment(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(appointmentTableView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Appointment Selected");
            alert.setHeaderText("No appointment selected");
            alert.setContentText("Please select an appointment to delete");
            alert.showAndWait();
        } else{
            theAppointment = appointmentTableView.getSelectionModel().getSelectedItem();
            AppointmentDaoImpl.deleteAppointment(theAppointment.getAppointment_ID());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Deleted Appointment");
            alert.setHeaderText("Appointment ID " + theAppointment.getAppointment_ID() + " Appointment Type: " + theAppointment.getType() + " was cancelled.");
            alert.setContentText("Click ok to confirm");
            alert.showAndWait();
            populateTable();
        }
    }

    /**
     * initialize method to populate appointments table
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      populateTable();
    }

    public void populateTable(){
        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("Contact_Name"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("Start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("End"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("User_ID"));

        try {
            ObservableList<Appointments> appointments = AppointmentDaoImpl.getAppointments();

            appointmentTableView.setItems(appointments);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * filterByWeek method to filter appointments by current week
     * @param list
     */
    private void filterByWeek(ObservableList<Appointments> list) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime week = LocalDateTime.now().plusDays(7);

        FilteredList<Appointments> currentWeek = new FilteredList<>(list);

        currentWeek.setPredicate(appointment ->
        {
            LocalDateTime date = appointment.getStart();
            if(date.isEqual(now) ){
                return true;
            }
            if(date.isAfter(now) && date.isBefore(week)){
                return true;
            }
            if(date.isEqual(week)){
                return true;
            }
            return false;
        });
        appointmentTableView.setItems(currentWeek);


    }

    /**
     * filterByMonth method to filter appointments by current month
     * @param list
     */
    private void filterByMonth(ObservableList<Appointments> list) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());
        LocalDateTime beginningOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());

        FilteredList<Appointments> currentMonth = new FilteredList<>(list);
        currentMonth.setPredicate(appointment -> {
            LocalDateTime date = appointment.getStart();

            if(date.isEqual(now)){
                return true;
            }
            if((date.isEqual(beginningOfMonth) || date.isAfter(beginningOfMonth)) && date.isBefore(endOfMonth)){
                return true;
            }
            if(date.isEqual(endOfMonth)){
                return true;
            }
            return false;
        });
        appointmentTableView.setItems(currentMonth);

    }

    /**
     * onExit method to close the application
     * @param actionEvent
     * @throws SQLException
     */
    public void onExit(ActionEvent actionEvent) throws SQLException {
        DbConnection.closeConnection();
        System.exit(0);
    }

    /**
     * onAllView method to view all appointments
     * @param actionEvent
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void onAllView(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ObservableList<Appointments> appointmentsObservableList = AppointmentDaoImpl.getAppointments();
        appointmentTableView.setItems(appointmentsObservableList);
    }

    /**
     * onGenerateReports method to go to reports screen
     * @param actionEvent
     * @throws IOException
     */
    public void onGenerateReports(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/damon/schedulingapplication/reports.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 760);
        stage.setTitle("Reports");
        stage.setScene(scene);
        stage.show();
    }
}
