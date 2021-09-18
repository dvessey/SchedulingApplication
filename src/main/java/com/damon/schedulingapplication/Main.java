package com.damon.schedulingapplication;

import com.damon.schedulingapplication.utils.DbConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Main class
 * @author Damon Vessey
 */
public class Main extends Application {
    /**
     * start method to load login screen
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Scheduling Application Log In");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * main method starting point of program
     * @param args
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        launch();
        //close db connection
        DbConnection.closeConnection();
    }
}