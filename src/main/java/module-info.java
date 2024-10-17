module org.collage.inventory {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens org.collage.inventory to javafx.fxml;
    exports org.collage.inventory;
    opens org.collage.inventory.controllers to javafx.base, javafx.fxml;
    exports org.collage.inventory.controllers;
    opens org.collage.inventory.model to javafx.base, javafx.fxml;
    exports org.collage.inventory.model;
}