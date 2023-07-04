package com.example.bike_rent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class RefundController implements Security{
    public static int mandate_tag = 0;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Label helloLabel;
    @FXML
    private ComboBox<Integer> reserv_no;
    @FXML
    private Button agree_but;
    @FXML
    private DatePicker return_date;
    @FXML
    private ComboBox<Integer> client_id;

    @FXML
    private Button ret_but;
    @FXML
    void initialize() throws SQLException {
        DataBaseHandler dbhandler = DataBaseHandler.getInstance();
        client_id.setItems(dbhandler.getIdsOfRclients());
        client_id.setOnAction(actionEvent -> {
            try {
                reserv_no.setItems(dbhandler.getClientResBikeId(client_id.getValue()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        agree_but.setOnAction(actionEvent -> {
            try {
                dbhandler.return_bike(reserv_no.getValue(), return_date.getValue());
                System.out.println("Велик на базе");
                agree_but.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("main_shopadmin.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        ret_but.setOnAction(actionEvent -> {
            ret_but.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("main_shopadmin.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });

    }

}
