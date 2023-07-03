package com.example.bike_rent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

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
        shoplist.setItems(dbhandler.getColumnBoxData(Const.SHOP_TABLE, Const.SHOP_NAME));

        agree.setOnAction(actionEvent -> {
            String choosed_model = modellist.getValue();
            String choosed_shop = shoplist.getValue();
            try {
                dbhandler.reserve_bike(choosed_model, choosed_shop, issue_date.getValue());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("FLAG");
        });
    }

}

