package com.example.bike_rent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Label errorlabel;

    @FXML
    private Button authorizebutton;

    @FXML
    private TextField loginfield;

    @FXML
    private Button mainregbutton;

    @FXML
    private PasswordField passwordfield;

    @FXML
    void initialize() {
        authorizebutton.setOnAction(actionEvent -> {
            String logtext = loginfield.getText().trim();
            String passtext = passwordfield.getText().trim();
            if (!logtext.equals("") && !passtext.equals("")){
                try {
                    loginuser(logtext, passtext);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                System.out.println("Заполните поля!");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Ошибка авторизации:");
                alert.setContentText("Заполните поля!");
                alert.showAndWait();
            }
        });
        mainregbutton.setOnAction(actionEvent -> {
            mainregbutton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("registration.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

    }
    private void loginuser(String login, String password) throws SQLException {
        DataBaseHandler dbhandler = DataBaseHandler.getInstance();
        ResultSet result = dbhandler.getUser(login, password);
        int count = 0;
        while (result.next()){
            count++;
        }
        if (count >= 1){
            System.out.println("Авторизация выполнена успешно! (f-true)");
            authorizebutton.getScene().getWindow().hide();
            Authorization.setLogin(login);
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
            stage.showAndWait();
        }
        else {
            System.out.println("Ошибка авторизации!");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка авторизации:");
            alert.setContentText("Ошибка в логине или пароле. Проверьте корректность введённых данных!");
            alert.showAndWait();
        }
    }
}