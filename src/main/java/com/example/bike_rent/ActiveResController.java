package com.example.bike_rent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ActiveResController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<RTable> act_table;
    @FXML
    private TableView<RTable> alltab;

    @FXML
    private Tab active_table;

    @FXML
    private Tab all_table;

    @FXML
    private TabPane tabpanee;
//    @FXML
//    private TableView<RTable> tableView = new TableView<>();
    @FXML
    private Button ret_button;

    @FXML
    private TableColumn<RTable, Integer> id = new TableColumn<>("ID");
    @FXML
    private TableColumn<RTable, String> clientId = new TableColumn<>("ID Клиента");
    @FXML
    private TableColumn<RTable, Integer> bikeId = new TableColumn<>("ID Велосипеда");
    @FXML
    private TableColumn<RTable, LocalDate> Issue_date = new TableColumn<>("Дата выдачи");
    @FXML
    private TableColumn<RTable, Integer> id1 = new TableColumn<>("ID");
    @FXML
    private TableColumn<RTable, String> clientId1 = new TableColumn<>("ID Клиента");
    @FXML
    private TableColumn<RTable, Integer> bikeId1 = new TableColumn<>("ID Велосипеда");
    @FXML
    private TableColumn<RTable, LocalDate> Issue_date1 = new TableColumn<>("Дата выдачи");
    @FXML
    private TableColumn<RTable, LocalDate> return_date1 = new TableColumn<>("Дата возврата");


    @FXML
    void initialize() throws SQLException {
        DataBaseHandler dbhandler = DataBaseHandler.getInstance();

        act_table.setItems(dbhandler.getActiveReservations());

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        clientId.setCellValueFactory(new PropertyValueFactory<>("client_id"));
        bikeId.setCellValueFactory(new PropertyValueFactory<>("bike_id"));
        Issue_date.setCellValueFactory(new PropertyValueFactory<>("Issue_date"));
        //System.out.println(dbhandler.getActiveReservations().getItems());

        alltab.setItems(dbhandler.getAllReservations());
        id1.setCellValueFactory(new PropertyValueFactory<>("id"));
        clientId1.setCellValueFactory(new PropertyValueFactory<>("client_id"));
        bikeId1.setCellValueFactory(new PropertyValueFactory<>("bike_id"));
        Issue_date1.setCellValueFactory(new PropertyValueFactory<>("Issue_date"));
        return_date1.setCellValueFactory(new PropertyValueFactory<>("return_date"));

        ret_button.setOnAction(actionEvent -> {
            ret_button.getScene().getWindow().hide();
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

