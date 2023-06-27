module com.example.bike_rent {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.bike_rent to javafx.fxml;
    exports com.example.bike_rent;
}