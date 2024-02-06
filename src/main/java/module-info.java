module com.project {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.project to javafx.fxml;
    exports com.project;
    exports com.project.model;
    exports com.project.controller;
    opens com.project.controller to javafx.fxml;
}
