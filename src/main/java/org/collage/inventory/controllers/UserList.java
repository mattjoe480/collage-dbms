package org.collage.inventory.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.collage.inventory.UIHelper;
import org.collage.inventory.model.Role;
import org.collage.inventory.model.UserModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserList implements Initializable {
    @FXML
    public TableView userListTable;
    @FXML
    public TableColumn userNameCol;
    @FXML
    public TableColumn roleCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert DataBase.instance.getCurrentRole() != Role.ADMIN: "Current user is not admin";

        userNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        updateTable();
    }
    public void updateTable() {
        try {
            userListTable.setItems(FXCollections.observableArrayList(DataBase.instance.getAllUsers()));
        } catch (SQLException e) {
            UIHelper.showError("Can't get user list");
            System.out.println(e.getMessage());
        }
    }
    public void upgradeRole(MouseEvent mouseEvent) {
        try {
        UserModel user = (UserModel) userListTable.getSelectionModel().getSelectedItem();
            DataBase.instance.setRole(user, Role.MANAGER);
            updateTable();
        } catch (Exception e) {
            UIHelper.showError("Can't update role");
        }
    }

    public void removeUser(MouseEvent mouseEvent) {
        try {
            UserModel user = (UserModel) userListTable.getSelectionModel().getSelectedItem();
            DataBase.instance.deleteUser(user);
            updateTable();
        } catch (Exception e) {
            UIHelper.showError("Can't delete user");
            System.out.println(e.getMessage());
        }
    }
}
