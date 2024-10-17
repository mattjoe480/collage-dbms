package org.collage.inventory.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.collage.inventory.UIHelper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

import static org.collage.inventory.UIHelper.showError;

public class Register {
    @FXML
    public TextField userNameField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public PasswordField passwordConfirmField;
    private MouseEvent mouseEvent;

    public void register() {
        if (userNameField.getText().isBlank()) {
            showError("Username is required!");
        }
        else if (passwordField.getText().isBlank()) {
            showError("Password is required!");
        }
        else if (passwordConfirmField.getText().isBlank()) {
            showError("Password is required!");
        }
        else if (!passwordField.getText().trim().equals(passwordConfirmField.getText().trim())) {
            showError("Passwords do not match!");
            System.out.println(passwordField.getText() + " " + passwordConfirmField.getText());
        }
        else {
            try {
                DataBase.instance.addNewUser(userNameField.getText(),
                        passwordField.getText(), "user");
            } catch (SQLException e) {
                showError("Cannot register user!");
                System.out.println(e.getMessage());
            }
        }
    }

    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER){
            register();
        }
    }

    public void onBack(MouseEvent mouseEvent) {
        this.mouseEvent = mouseEvent;
        try {
            UIHelper.setWindow("login-view.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
