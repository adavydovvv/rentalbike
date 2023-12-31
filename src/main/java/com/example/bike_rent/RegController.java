package com.example.bike_rent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegController{

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
            ArrayList<TextField> array = new ArrayList<>();
            array.add(clientaddress);
            array.add(clientname);
            array.add(passpnum);
            array.add(passpser);
            array.add(regemail);
            array.add(clientaddress);
            array.add(reglogin);
            array.add(regpassword);
            boolean flag = false;
            for (int i = 0; i < array.size(); i++){
                if (Objects.equals(array.get(i).getText(), "")){
                    flag = true;
                }
            }
            if (!flag){
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
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Ошибка при регистрации:");
                alert.setContentText("Заполните все поля!");
                alert.showAndWait();
            }
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