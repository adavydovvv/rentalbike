package com.example.bike_rent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ClientResHisController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<RTable, LocalDate> isdate;

    @FXML
    private TableColumn<RTable, LocalDate> isdate1;

    @FXML
    private TableColumn<RTable, String> model;

    @FXML
    private TableColumn<RTable, String> model1;

    @FXML
    private TableColumn<RTable, LocalDate> retdate1;

    @FXML
    private Button return_button;

    @FXML
    private TableColumn<RTable, String> shop;

    @FXML
    private TableColumn<RTable, String> shop1;
    @FXML
    private TableView<RTable> table1;

    @FXML
    private TableView<RTable> table2;


    @FXML
    void initialize() throws SQLException {
        DataBaseHandler dbhandler = DataBaseHandler.getInstance();

        table1.setItems(dbhandler.getActiveClientRes());

        model.setCellValueFactory(new PropertyValueFactory<>("model"));
        shop.setCellValueFactory(new PropertyValueFactory<>("issue_shop"));
        isdate.setCellValueFactory(new PropertyValueFactory<>("issue_date"));

        table2.setItems(dbhandler.getHistoryClientRes());

        model1.setCellValueFactory(new PropertyValueFactory<>("model"));
        shop1.setCellValueFactory(new PropertyValueFactory<>("issue_shop"));
        isdate1.setCellValueFactory(new PropertyValueFactory<>("issue_date"));
        retdate1.setCellValueFactory(new PropertyValueFactory<>("return_date"));

        return_button.setOnAction(actionEvent -> {
            return_button.getScene().getWindow().hide();
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
        });

    }

}
