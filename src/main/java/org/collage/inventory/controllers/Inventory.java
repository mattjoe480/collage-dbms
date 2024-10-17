package org.collage.inventory.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.collage.inventory.UIHelper;
import org.collage.inventory.model.InventoryModel;
import org.collage.inventory.model.Role;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class Inventory implements Initializable {
    @FXML
    public TableColumn<InventoryModel, String> itemNameCol;
    @FXML
    public TableColumn<InventoryModel, String> itemQuantityCol;
    @FXML
    public Button addItemBTN;
    @FXML
    public TextField itemNameField;
    @FXML
    public TextField itemQuanityField;
    @FXML
    private TableView inventoryTable;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initializing inventory");
        itemNameCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        itemQuantityCol.setCellValueFactory(new PropertyValueFactory<>("itemQuantity"));
        try {
            inventoryTable.setItems(FXCollections.observableArrayList(DataBase.instance.getAllInventory()));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public void handleAddItem(MouseEvent actionEvent) {
        String itemName = itemNameField.getText();
        int itemQuantity = Integer.parseInt(itemQuanityField.getText());
        System.out.println("Adding item " + itemName + " with quantity " + itemQuantity);
        if (itemName.isBlank()  || itemQuantity <= 0) {
            if (itemName.isBlank() )
                UIHelper.showError("Please enter a valid item name");
            else
                UIHelper.showError("Please enter a valid item quantity");
            return;
        }
        try {
            DataBase.instance.addToInventory(itemName, itemQuantity);
            inventoryTable.setItems(FXCollections.observableArrayList(DataBase.instance.getAllInventory()));
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeItem(MouseEvent mouseEvent) {
        try {
            InventoryModel item = (InventoryModel) inventoryTable.getSelectionModel().getSelectedItem();
            DataBase.instance.removeFromInventory(item.getItemName());
            inventoryTable.setItems(FXCollections.observableArrayList(DataBase.instance.getAllInventory()));
        }
        catch (Exception e) {
            UIHelper.showError("Cannot remove item ");
        }
    }
}
