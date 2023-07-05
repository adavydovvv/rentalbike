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

public class SAdminController implements Security{
    public static int mandate_tag = 1;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button active_reserv;

    @FXML
    private Button all_reserv;

    @FXML
    private Button bikes_inshop;

    @FXML
    private Button return_bike;
    @FXML
    private Button system_exit;
    @FXML
    private Label shopname;

    @FXML
    void initialize() throws SQLException {
        DataBaseHandler dbhandler = DataBaseHandler.getInstance();
        shopname.setText("Магазин " + dbhandler.getShopName(Shop.getAdmin_id()));
        system_exit.setOnAction(actionEvent -> {
            Authorization.setLogin("");
            system_exit.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("hello-view.fxml"));
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
        bikes_inshop.setOnAction(actionEvent -> {
            bikes_inshop.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("all_shopbikes.fxml"));
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
        return_bike.setOnAction(actionEvent -> {
            try {
                if (dbhandler.getActiveReservations().size() == 0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Внимание");
                    alert.setHeaderText("Внимание");
                    alert.setContentText("На данный момент нет активных бронирований!");
                    alert.showAndWait();
                }
                else {
                    return_bike.getScene().getWindow().hide();
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
        active_reserv.setOnAction(actionEvent -> {
            active_reserv.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("active_res.fxml"));
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