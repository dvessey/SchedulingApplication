package com.damon.schedulingapplication.Controller;

import com.damon.schedulingapplication.DAO.AppointmentDaoImpl;
import com.damon.schedulingapplication.Model.Appointments;
import com.damon.schedulingapplication.Model.Customers;
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
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ResourceBundle;
import java.util.function.Predicate;

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

    public static Appointments getTheAppointment() {
        return theAppointment;
    }


    public void onMonthView(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ObservableList<Appointments> appointments = AppointmentDaoImpl.getAppointments();
        filterByWeek(appointments);
    }

    public void onWeekView(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ObservableList<Appointments> appointments = AppointmentDaoImpl.getAppointments();
        filterByMonth(appointments);
    }

    public void onShowCustomer(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/damon/schedulingapplication/customer.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("Customer");
        stage.setScene(scene);
        stage.show();
    }

    public void onAddAppointment(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/damon/schedulingapplication/addAppointment.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("Appointment");
        stage.setScene(scene);
        stage.show();
    }

    public void onEditAppointment(ActionEvent actionEvent) throws IOException {
        theAppointment = appointmentTableView.getSelectionModel().getSelectedItem();
        System.out.println("IN AppointmentsController onEditAppointment - contactID: " + theAppointment.getContact_ID());
        System.out.println("IN AppointmentsController onEditAppointment - customerID: " + theAppointment.getCustomer_ID());
        System.out.println("IN AppointmentsController onEditAppointment - createDate: " + theAppointment.getCreate_Date());
        System.out.println("IN AppointmentsController onEditAppointment - createdBy: " + theAppointment.getCreated_By());
        Parent root = FXMLLoader.load(getClass().getResource("/com/damon/schedulingapplication/editAppointment.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("Appointment");
        stage.setScene(scene);
        stage.show();
    }

    public void onDeleteAppointment(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        theAppointment = appointmentTableView.getSelectionModel().getSelectedItem();
        AppointmentDaoImpl.deleteAppointment(theAppointment.getAppointment_ID());
        System.out.println("Deletion Successful");
        populateTable();
    }

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

        try {
            ObservableList<Appointments> appointments = AppointmentDaoImpl.getAppointments();

            appointmentTableView.setItems(appointments);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void filterByWeek(ObservableList<Appointments> list) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime week = LocalDateTime.now().plusDays(7);

        FilteredList<Appointments> currentWeek = new FilteredList<>(list);
        currentWeek.setPredicate(appointment ->
        {
            LocalDateTime date = appointment.getStart();
            return (date.isEqual(now) || date.isAfter(now)) && (date.isBefore(week) || date.isEqual(week));
        });
        appointmentTableView.setItems(currentWeek);
    }

    private void filterByMonth(ObservableList<Appointments> list) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());

        FilteredList<Appointments> result = new FilteredList<>(list);
        result.setPredicate(appointment -> {
            LocalDateTime date = appointment.getStart();
            return (date.isEqual(now) || date.isAfter(now)) && (date.isBefore(endOfMonth) || date.isEqual(endOfMonth));
        });
        appointmentTableView.setItems(result);

    }

    public void onExit(ActionEvent actionEvent) throws SQLException {
        DbConnection.closeConnection();
        System.exit(0);
    }

    public void onAllView(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ObservableList<Appointments> appointmentsObservableList = AppointmentDaoImpl.getAppointments();
        appointmentTableView.setItems(appointmentsObservableList);
    }
}
