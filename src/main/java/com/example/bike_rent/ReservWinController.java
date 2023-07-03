package com.example.bike_rent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ReservWinController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private DatePicker issue_date;

    @FXML
    private Label helloLabel;
    @FXML
    private Button agree;

    @FXML
    private ComboBox<String> modellist;

    @FXML
    private ComboBox<String> shoplist;

    @FXML
    void initialize() throws SQLException {
        DataBaseHandler dbhandler = DataBaseHandler.getInstance();
        modellist.setItems(dbhandler.getColumnBoxData(Const.MODEL_TABLE, Const.MODEL_NAME));
        modellist.setOnAction(actionEvent -> {
            try {
                shoplist.setItems(dbhandler.getColumnBoxShops(modellist.getValue()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        agree.setOnAction(actionEvent -> {
            String choosed_model = modellist.getValue();
            String choosed_shop = shoplist.getValue();
            try {
                dbhandler.reserve_bike(choosed_model, choosed_shop, issue_date.getValue());
               agree.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("main-win.fxml"));
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
            System.out.println("FLAG");
        });
    }

}

