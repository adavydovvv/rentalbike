package com.example.bike_rent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegController {

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
    private TextField regemail;

    @FXML
    private TextField reglogin;

    @FXML
    private PasswordField regpassword;

    @FXML
    void initialize() {
        DataBaseHandler dbhandler = DataBaseHandler.getInstance();
        acceptbutton.setOnAction(actionEvent -> {
            dbhandler.signUpUser(clientname.getText(), clientaddress.getText(), passpser.getText(),
                    passpnum.getText(), reglogin.getText(), regpassword.getText(), regemail.getText());
            System.out.println("кнопка нажалась");
        });
    }

}