package org.collage.inventory.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import org.collage.inventory.UIHelper;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    @FXML
    public PasswordField passwordField;
    @FXML
    public VBox loginBox;
    @FXML
    private TextField userField;

    private String userName;
    private String password;

    @FXML
    protected void onLoginButtonClick() {
        System.out.println("Login invoked " + userField.getText() + " " + passwordField.getText());
        if (userField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Username or password is empty");
            alert.showAndWait();
        }else {
            userName = userField.getText();
            password = passwordField.getText();
            try {
                System.out.println("Connecting to server...");
                login();
            }catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    private void login() throws SQLException, IOException {
        Boolean canLogin = DataBase.instance.login(userName, password);
        if (Boolean.FALSE.equals(canLogin)) {
            UIHelper.showError("Incorrect username or password");
            System.out.println("Cannot login");
        }
    }

    public void register(ActionEvent actionEvent) {
        try {
            UIHelper.setWindow("register.fxml");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            try {
                login();
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}