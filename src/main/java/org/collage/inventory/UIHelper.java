package org.collage.inventory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class UIHelper {
    private static Stage window;

    public static void setWindow(String viewName) throws IOException {
        Parent inventoryPage = FXMLLoader.load(Objects.requireNonNull(UIHelper.class.getResource(viewName)));
        Scene scene = new Scene(inventoryPage);
        window.setScene(scene);
        window.show();
    }

    public static void setStage(Stage stage) {
        window = stage;
    }
    public static void showError(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText(null);
        alert.setContentText(error);
        alert.showAndWait();
    }

}
