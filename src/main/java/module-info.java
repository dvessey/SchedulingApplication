module com.damon.schedulingapplication {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.sql;


    opens com.damon.schedulingapplication to javafx.graphics;
    exports com.damon.schedulingapplication;
    exports com.damon.schedulingapplication.Model;
    opens com.damon.schedulingapplication.Model to javafx.fxml;
    exports com.damon.schedulingapplication.Controller;
    opens com.damon.schedulingapplication.Controller to javafx.fxml;
}
