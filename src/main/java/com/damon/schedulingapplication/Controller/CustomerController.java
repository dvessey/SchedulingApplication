package com.damon.schedulingapplication.Controller;

import com.damon.schedulingapplication.DAO.AppointmentDaoImpl;
import com.damon.schedulingapplication.DAO.CustomerDaoImpl;
import com.damon.schedulingapplication.Model.Appointments;
import com.damon.schedulingapplication.Model.Countries;
import com.damon.schedulingapplication.Model.Customers;
import com.damon.schedulingapplication.Model.FirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * CustomerController class to handle customers
 * @author Damon Vessey
 */
public class CustomerController implements Initializable {
    public TableView<Customers> customerTableView;
    public TableColumn<Customers, Integer> customerIdCol;
    public TableColumn<Customers, String> customerNameCol;
    public TableColumn<Customers, String> customerAddressCol;
    public TableColumn<Customers, String> customerPostalCodeCol;
    public TableColumn<Customers, String> customerPhoneCol;
    public TableColumn<Customers, String> customerStateCol;
    public TableColumn<Customers, String> customerCountryCol;
    public Button addCustomerButton;
    public Button editCustomerButton;
    public Button deleteCustomerButton;
    public Button cancelButton;
    public TableView<Appointments> selectedCustomerAppointmentsTableView;
    public TableColumn<Appointments, Integer> appointmentIdCol;
    public TableColumn<Appointments, String> appointmentTitleCol;
    public TableColumn<Appointments, String> appointmentDescriptionCol;
    public TableColumn<Appointments, String> appointmentLocationCol;
    public TableColumn<Appointments, String> appointmentContactCol;
    public TableColumn<Appointments, String> appointmentTypeCol;
    public TableColumn<Appointments, LocalDateTime> appointmentStartCol;
    public TableColumn<Appointments, LocalDateTime> appointmentEndCol;
    public TableColumn<Appointments, Integer> appointmentCustomerIdCol;

    private static Customers theCustomer = null;
    public static Customers getTheCustomer(){
        return theCustomer;
    }

    /**
     * onAddCustomer method to add a customer
     * @param actionEvent
     * @throws IOException
     */
    public void onAddCustomer(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/damon/schedulingapplication/addCustomer.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("Add Customer");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * onEditCustomer method to edit selected customer
     * @param actionEvent
     * @throws IOException
     */
    public void onEditCustomer(ActionEvent actionEvent) throws IOException {
      if(customerTableView.getSelectionModel().isEmpty()) {
          Alert alert = new Alert(Alert.AlertType.WARNING);
          alert.setTitle("No customer selected");
          alert.setHeaderText("No customer selected");
          alert.setContentText("Please select a customer to edit");
          alert.showAndWait();
      } else{
            theCustomer = customerTableView.getSelectionModel().getSelectedItem();
            Parent root = FXMLLoader.load(getClass().getResource("/com/damon/schedulingapplication/editCustomer.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1000, 700);
            stage.setTitle("Edit Customer");
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * onDeleteCustomer method to delete selected customer and all appointments associated with that customer
     * @param actionEvent
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void onDeleteCustomer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
            if(customerTableView.getSelectionModel().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No customer selected");
                alert.setHeaderText("No customer selected");
                alert.setContentText("Please select a customer to edit");
                alert.showAndWait();
            } else{

            //all customer's appointments must be deleted first
            theCustomer = customerTableView.getSelectionModel().getSelectedItem();
            int theCustomerId = theCustomer.getCustomer_ID();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Customer");
            alert.setHeaderText("The appointments associated with Customer ID: " + theCustomerId + " will also be deleted.");
            alert.setContentText("Click ok to confirm");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                CustomerDaoImpl.deleteCustomer(theCustomerId);
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Customer Deleted");
                alert2.setHeaderText("Customer ID: " + theCustomerId + " and the customer's appointments were deleted.");
                alert2.setContentText("Click ok to confirm");
                alert2.showAndWait();
                selectedCustomerAppointmentsTableView.setItems(null);
                populateTable();
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
     * populateTable method to populate customer table
     */
    public void populateTable(){
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
        customerPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("Postal_Code"));
        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        customerStateCol.setCellValueFactory(new PropertyValueFactory<>("Division"));
        customerCountryCol.setCellValueFactory(new PropertyValueFactory<>("Country"));

        ObservableList<Customers> customersList = null;
        try {
            customersList = CustomerDaoImpl.getCustomersCountry();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        customerTableView.setItems(customersList);

        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        appointmentTitleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        appointmentDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        appointmentLocationCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
        appointmentContactCol.setCellValueFactory(new PropertyValueFactory<>("Contact_Name"));
        appointmentTypeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        appointmentStartCol.setCellValueFactory(new PropertyValueFactory<>("Start"));
        appointmentEndCol.setCellValueFactory(new PropertyValueFactory<>("End"));
        appointmentCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));

    }


    /**
     * initialize method to populate customer table
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateTable();
    }

    /**
     * onShowAppointments method to show appointments associated with the selected customer
     * @param actionEvent
     */
    public void onShowAppointments(ActionEvent actionEvent) {
        if(customerTableView.getSelectionModel().isEmpty()) {
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("No customer selected");
            alert2.setHeaderText("No customer selected");
            alert2.setContentText("Please select a customer to show their appointments.");
            alert2.showAndWait();
        } else{
            theCustomer = customerTableView.getSelectionModel().getSelectedItem();
            int theCustomerId = theCustomer.getCustomer_ID();

            ObservableList<Appointments> appointmentList = null;
            try {
                appointmentList = AppointmentDaoImpl.getAppointments(theCustomerId);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            selectedCustomerAppointmentsTableView.setItems(appointmentList);
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
}
