module com.example.javafx_demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafx_demo to javafx.fxml;
    exports com.example.javafx_demo;
}