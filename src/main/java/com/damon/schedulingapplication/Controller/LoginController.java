package com.damon.schedulingapplication.Controller;

import com.damon.schedulingapplication.DAO.AppointmentDaoImpl;
import com.damon.schedulingapplication.DAO.UserDaoImpl;
import com.damon.schedulingapplication.Model.Appointments;
import com.damon.schedulingapplication.Model.Users;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * LoginController class to handle login
 * @author Damon Vessey
 */
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
    private boolean hasAppointment = false;

   private static Users theUser = null;

   public static Users getTheUser(){
       return theUser;
   }

    /**
     * onLogIn method to check username and password and if an appointment is within 15 minutes
     * <p><b>lambda expression to check if an appointment is within 15 minutes of logging in</b></p>
     * @param actionEvent
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    public void onLogIn(ActionEvent actionEvent) throws ClassNotFoundException, SQLException, IOException {
        //Check if username and password entered matches a user in database, if login successful go to appointments view
        theUser = UserDaoImpl.getUser(usernameTextField.getText());
        if(theUser == null){
            String fileName = "login_activity.txt", activity;
            String userName = usernameTextField.getText();
                FileWriter fileWriter = null;
                try {
                    fileWriter = new FileWriter(fileName, true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                PrintWriter outputFile = new PrintWriter(fileWriter);
            LocalDateTime loginDate = LocalDateTime.now();
            Timestamp activityNow = Timestamp.valueOf(loginDate);

            outputFile.println("User: " + userName + " does not exist, could not authenticate on " + loginDate + " Timestamp: " + activityNow);
            getErrorMessage();
            outputFile.close();
            System.out.println("NO USER EXISTS");
        }else {
            String fileName = "login_activity.txt", activity;
            String userName = usernameTextField.getText();
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(fileName, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            PrintWriter outputFile = new PrintWriter(fileWriter);
            LocalDateTime loginDate = LocalDateTime.now();
            Timestamp activityNow = Timestamp.valueOf(loginDate);

            if (passwordField.getText().equals(theUser.getPassword())) {
                outputFile.println("User: " + userName + " has authenticated successfully on " + loginDate + " Timestamp: " + activityNow);

                //CHECK FOR APPOINTMENTS WITHIN 15 Minutes
                LocalDateTime now = LocalDateTime.now();

                ObservableList<Appointments> appointments = null;
                try {
                    appointments = AppointmentDaoImpl.getUserAppointments(theUser.getUserID());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                appointments.forEach(appointment -> {
                    LocalDateTime start = Timestamp.valueOf(appointment.getStart()).toLocalDateTime();
                    long difference = ChronoUnit.MINUTES.between(now, start);
                    long interval = difference + 1;

                    if (interval > 0 && interval <= 15) {
                        hasAppointment = true;
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Upcoming Appointment");
                        alert.setHeaderText("You have an appointment with Appointment Id: " + appointment.getAppointment_ID() + " at " + start + " in " + interval + " minutes");
                        alert.setContentText("Click ok to confirm");
                        alert.showAndWait();
                    }
                });

                if (!hasAppointment) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Upcoming Appointment");
                    alert.setHeaderText("You don't have any appointments in the next 15 minutes");
                    alert.setContentText("Click ok to confirm");
                    alert.showAndWait();
                }

                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/com/damon/schedulingapplication/appointments.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, 1105, 700);
                stage.setTitle("Appointments");
                stage.setScene(scene);
                stage.show();

            } else {
                outputFile.println("User: " + userName + " has failed to authenticate on " + loginDate + " Timestamp: " + activityNow);
                getErrorMessage();
            }
            outputFile.close();
        }
    }

    /**
     * initialize method to initialize the form
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Since Intellij uses Maven, the compiler is looking for the resource bundle under src/main/resources
        resourceBundle = ResourceBundle.getBundle("com.damon.schedulingapplication.Nat", Locale.getDefault());
        //resourceBundle = ResourceBundle.getBundle("com.damon.schedulingapplication.Nat", Locale.FRENCH);
        loginLabel.setText(resourceBundle.getString("Login"));
        usernameLabel.setText(resourceBundle.getString("Username"));
        passwordLabel.setText(resourceBundle.getString("Password"));
        userLocationLabel.setText(resourceBundle.getString("Location"));
        logInButton.setText(resourceBundle.getString("Login"));
        userLocationField.setText(local.toString());
    }

    private void getErrorMessage(){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("com.damon.schedulingapplication.Nat", Locale.getDefault());
        //ResourceBundle resourceBundle = ResourceBundle.getBundle("com.damon.schedulingapplication.Nat", Locale.FRENCH);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(resourceBundle.getString("Login"));
        alert.setHeaderText(resourceBundle.getString("error"));
        alert.setContentText(resourceBundle.getString("correction"));
        alert.showAndWait();
    }
}
