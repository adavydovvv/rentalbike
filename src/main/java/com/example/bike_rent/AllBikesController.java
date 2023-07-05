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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AllBikesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<RTable, Integer> id = new TableColumn<>("Id");

    @FXML
    private TableColumn<RTable, String> model = new TableColumn<>("Model");

    @FXML
    private Button return_button;

    @FXML
    private TableColumn<RTable, String> status = new TableColumn<>("Status");

    @FXML
    private TableView<RTable> tableView;

    @FXML
    void initialize() throws SQLException {
        DataBaseHandler dbhandler = DataBaseHandler.getInstance();

        tableView.setItems(dbhandler.getAllBikes());

        id.setCellValueFactory(new PropertyValueFactory<>("bike_id"));
        model.setCellValueFactory(new PropertyValueFactory<>("model"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));

        return_button.setOnAction(actionEvent -> {
            return_button.getScene().getWindow().hide();
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
