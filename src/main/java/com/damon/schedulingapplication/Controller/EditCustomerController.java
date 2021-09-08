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

public class EditCustomerController implements Initializable {
    public TextField custName;
    public TextField custAddress;
    public TextField custPostalCode;
    public TextField custPhoneNumber;
    public ComboBox<FirstLevelDivisions> stateComboBox;
    public ComboBox<Countries> countryComboBox;
    public Button saveCustomerButton;
    public Button cancelButton;
    public TextField customerIdLabel;

    private Customers theCustomer;

    public void onCountrySelect(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Countries selectedCountry = countryComboBox.getSelectionModel().getSelectedItem();
        int selectedCountryId = selectedCountry.getCountry_ID();
        ObservableList<FirstLevelDivisions> states = CustomerDaoImpl.getFirstLevelDivisions(selectedCountryId);
        stateComboBox.setItems(states);
    }

    public void onSaveCustomer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        FirstLevelDivisions state = stateComboBox.getSelectionModel().getSelectedItem();
        Countries country = countryComboBox.getSelectionModel().getSelectedItem();

        theCustomer.setLast_Update(Timestamp.valueOf(LocalDateTime.now()));
        theCustomer.setLast_Updated_By(Users.getUser_name());
        theCustomer.setCountry_ID(theCustomer.getCountry_ID());

        theCustomer.setCustomer_Name(custName.getText());
        theCustomer.setAddress(custAddress.getText());
        theCustomer.setPostal_Code(custPostalCode.getText());
        theCustomer.setPhone(custPhoneNumber.getText());
        theCustomer.setDivision_ID(state.getDivision_ID());
        theCustomer.setCountry(country.getCountry());

        CustomerDaoImpl.updateCustomer(theCustomer);

        goToCustomerScreen(actionEvent);

    }

    public void onCancel(ActionEvent actionEvent) throws IOException {
        goToCustomerScreen(actionEvent);
    }

    public void goToCustomerScreen(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/damon/schedulingapplication/customer.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        theCustomer = CustomerController.getTheCustomer();
        Integer theCustomerId = theCustomer.getCustomer_ID();
        customerIdLabel.setText(String.valueOf(theCustomerId));
        String theCustomerName = theCustomer.getCustomer_Name();;
        custName.setText(theCustomerName);
        String theCustomerAddress = theCustomer.getAddress();
        custAddress.setText(theCustomerAddress);
        String theCustomerPostalCode = theCustomer.getPostal_Code();
        custPostalCode.setText(theCustomerPostalCode);
        String theCustomerPhone = theCustomer.getPhone();
        custPhoneNumber.setText(theCustomerPhone);
        Integer theCustomerDivisionId = theCustomer.getDivision_ID();

        int theCustomerCountryId = theCustomer.getCountry_ID();
        Countries theCustomerCountry = null;
        try {
            theCustomerCountry = CustomerDaoImpl.getCountry(theCustomerCountryId);
            countryComboBox.setValue(theCustomerCountry);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        try {
          ObservableList<FirstLevelDivisions> customerDivision = CustomerDaoImpl.getFirstLevelDivisions(theCustomerCountryId);
          //String theCustomerDivision = customerDivision.get(0).getDivision();
          //Integer theCustomerDivisionId = customerDivision.get(0).getDivision_ID();
          stateComboBox.setValue(customerDivision.get(0));

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        try {
            countryComboBox.setItems(CustomerDaoImpl.getCountries());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        try {
            stateComboBox.setItems(CustomerDaoImpl.getFirstLevelDivisions(theCustomerCountryId));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
