package com.simcorp.investment_task.gui;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class ErrorDialog {
    public static void show(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(message);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

        alert.showAndWait();
    }
}
