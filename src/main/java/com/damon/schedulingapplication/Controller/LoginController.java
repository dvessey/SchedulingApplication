package com.damon.schedulingapplication.Controller;

import com.damon.schedulingapplication.DAO.UserDaoImpl;
import com.damon.schedulingapplication.Model.Users;
import com.damon.schedulingapplication.utils.DbConnection;
import com.damon.schedulingapplication.utils.DbQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public Label loginLabel;
    public Label usernameLabel;
    public TextField usernameTextField;
    public Label passwordLabel;
    public PasswordField passwordField;
    public Label userLocationLabel;
    public TextField userLocationField;
    public Button logInButton;
    public Label errorLabel;
    public Label emptyLabel;

    private final ZoneId local = ZoneId.systemDefault();

    public void onLogIn(ActionEvent actionEvent) throws ClassNotFoundException, SQLException, IOException {
        //Check if username and password entered matches a user in database, if login successful go to appointments view
        Users user = UserDaoImpl.getUser(usernameTextField.getText());
        assert user != null;
        if (passwordField.getText().equals(user.getPassword())){
            System.out.println(user.getUser_name() + " authenticated successfully");
            Parent root = FXMLLoader.load(getClass().getResource("/com/damon/schedulingapplication/appointments.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1000, 700);
            stage.setTitle("Appointments");
            stage.setScene(scene);
            stage.show();

        } else{
            System.out.println("ERROR WRONG PASSWORD!");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Since Intellij uses Maven, the compiler is looking for the resource bundle under src/main/resources
        resourceBundle = ResourceBundle.getBundle("com.damon.schedulingapplication.Nat", Locale.getDefault());
        loginLabel.setText(resourceBundle.getString("Login"));
        usernameLabel.setText(resourceBundle.getString("Username"));
        passwordLabel.setText(resourceBundle.getString("Password"));
        userLocationLabel.setText(resourceBundle.getString("Location"));
        logInButton.setText(resourceBundle.getString("Login"));
        userLocationField.setText(local.toString());

        //errorLabel.setText(resourceBundle.getString("error"));
        //emptyLabel.setText(resourceBundle.getString("empty"));
    }
}
