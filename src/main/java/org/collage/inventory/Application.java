package org.collage.inventory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.collage.inventory.controllers.DataBase;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 340);
        UIHelper.setStage(stage);
        stage.setTitle("Inventory management");
        stage.setScene(scene);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.show();
    }

    public static void main(String[] args) {
        try {
            //System.setOut(new PrintStream(OutputStream.nullOutputStream()));
            Class.forName("com.mysql.cj.jdbc.Driver");
            DataBase.instance = new DataBase();

        }catch (Exception e){
            e.printStackTrace();
        }

        launch();
    }
}