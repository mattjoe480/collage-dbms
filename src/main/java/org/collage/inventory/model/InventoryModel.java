package org.collage.inventory.model;

import javafx.beans.property.SimpleStringProperty;

public class InventoryModel {
    private String itemName;
    private String itemQuantity;


    public InventoryModel(String itemName, String itemQuantity) {
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {

        this.itemName = itemName;
    }

    public String getItemQuantity() {

        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
}
