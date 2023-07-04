package com.example.bike_rent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegController implements Security{
    public static int mandate_tag = 0;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button acceptbutton;

    @FXML
    private TextField clientaddress;

    @FXML
    private TextField clientname;

    @FXML
    private TextField passpnum;

    @FXML
    private TextField passpser;
    @FXML
    private Button ret_but;

    @FXML
    private TextField regemail;

    @FXML
    private TextField reglogin;

    @FXML
    private PasswordField regpassword;

    @FXML
    void initialize() {
        acceptbutton.setOnAction(actionEvent -> {
            signUpUser();
            acceptbutton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("hello-view.fxml"));
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
        ret_but.setOnAction(actionEvent -> {
            ret_but.getScene().getWindow().hide();
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
    }
    public void signUpUser(){
        DataBaseHandler dbhandler = DataBaseHandler.getInstance();
        dbhandler.signUpUser(clientname.getText(), clientaddress.getText(), passpser.getText(),
                passpnum.getText(), reglogin.getText(), regpassword.getText(), regemail.getText());
        System.out.println("!Пользователь успешно добавлен в базу данных!");
    }

}