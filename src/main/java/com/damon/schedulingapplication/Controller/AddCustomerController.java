package com.damon.schedulingapplication.Controller;

import com.damon.schedulingapplication.DAO.CustomerDaoImpl;
import com.damon.schedulingapplication.Model.Countries;
import com.damon.schedulingapplication.Model.Customers;
import com.damon.schedulingapplication.Model.FirstLevelDivisions;
import com.damon.schedulingapplication.Model.Users;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * AddCustomerController class to control adding of customers
 * @author Damon Vessey
 */
public class AddCustomerController implements Initializable {
    public TextField custName;
    public TextField custAddress;
    public TextField custPostalCode;
    public TextField custPhoneNumber;
    public ComboBox<FirstLevelDivisions> stateComboBox;
    public ComboBox<Countries> countryComboBox;
    public Button addCustomerButton;
    public Button cancelButton;

    private boolean hasErrors = false;

    /**
     * initialize method to initialize combo boxes
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            ObservableList<Countries> countries = CustomerDaoImpl.getCountries();
            countryComboBox.setItems(countries);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    /**
     * onAddCustomer method to add customer
     * @param actionEvent
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public void onAddCustomer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {

        try {
            validateBlank();
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            if(!hasErrors) {
                String customerName = custName.getText();
                String address = custAddress.getText();
                String postalCode = custPostalCode.getText();
                String phoneNumber = custPhoneNumber.getText();
                FirstLevelDivisions division = stateComboBox.getSelectionModel().getSelectedItem();
                int divisionId = division.getDivision_ID();

                LocalDateTime createDate = LocalDateTime.now();

                Users theUser = LoginController.getTheUser();
                String userName = theUser.getUser_name();

                String createdBy = userName;
                Timestamp lastUpdated = Timestamp.valueOf(LocalDateTime.now());
                String lastUpdatedBy = userName;
                String countryName = countryComboBox.getSelectionModel().getSelectedItem().toString();
                int countryId = countryComboBox.getSelectionModel().getSelectedItem().getCountry_ID();

                CustomerDaoImpl.addCustomer(new Customers(customerName, address, postalCode, phoneNumber, createDate, createdBy, lastUpdated, lastUpdatedBy, countryName, countryId, divisionId));
                goToHomeScreen(actionEvent);
            }
        }catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Blank fields");
            alert.setHeaderText("No fields can be blank");
            alert.setContentText("Please fill out the form");
            alert.showAndWait();
        }
    }

    /**
     * onCancel method to go back to home screen
     * @param actionEvent
     * @throws IOException
     */
    public void onCancel(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/damon/schedulingapplication/appointments.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * onCountrySelect method to populate state combo box based on the country selected
     * @param actionEvent
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void onCountrySelect(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Countries selectedCountry = countryComboBox.getSelectionModel().getSelectedItem();
        int selectedCountryId = selectedCountry.getCountry_ID();
        ObservableList<FirstLevelDivisions> states = CustomerDaoImpl.getFirstLevelDivisions(selectedCountryId);
        stateComboBox.setItems(states);
    }

    /**
     * goToHomeScreen method to go back to home screen
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
        if(custName.getText().trim().isBlank() || custAddress.getText().trim().isBlank() || custPhoneNumber.getText().trim().isBlank() ||
                custPostalCode.getText().trim().isBlank() || stateComboBox.getSelectionModel().isEmpty() || countryComboBox.getSelectionModel().isEmpty()) {
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
