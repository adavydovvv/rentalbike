package com.example.bike_rent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainWinController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button change_userdata;

    @FXML
    private Label helloLabel;

    @FXML
    private Button history_button;

    @FXML
    private Button ret_button;

    @FXML
    private Button zap_button;

    @FXML
    void initialize() {
        DataBaseHandler dbhandler = DataBaseHandler.getInstance();
        zap_button.setOnAction(actionEvent -> {
            zap_button.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("reserv-window.fxml"));
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
        ret_button.setOnAction(actionEvent -> {
            try {
                if (dbhandler.getClientResBikeId().size() == 0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText("Внимание!");
                    alert.setContentText("У вас нет активых бронирований!");
                    alert.showAndWait();
                }
                else {
                    ret_button.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("refund.fxml"));
                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        });
    }

}
