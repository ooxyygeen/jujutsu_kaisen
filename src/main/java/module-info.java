module com.example.javacoursework {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.GUI.javacoursework to javafx.fxml;
    exports com.GUI.javacoursework;
}