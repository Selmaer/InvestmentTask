package com.simcorp.investment_task.gui;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class WarningDialog {
    public static void show(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(message);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
//        stage.getIcons().add(new Image(Main.class.getResourceAsStream("icon/icon.png")));

        alert.showAndWait();
    }
}
