package com.simcorp.investment_task;

import com.simcorp.investment_task.data.InputData;
import com.simcorp.investment_task.gui.ErrorDialog;
import com.simcorp.investment_task.gui.InformationDialog;
import com.simcorp.investment_task.gui.MainWindow;
import javafx.application.Application;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeParseException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        MainWindow mainWindow = new MainWindow(primaryStage);
        Button calculateSumButton = mainWindow.getCalculateSumButton();

        calculateSumButton.setOnAction(e -> calculate(mainWindow));
    }

    private void calculate(MainWindow mainWindow) {
        InputData inputData;
        try {
            inputData = mainWindow.getInputData();
            CalculationModule module = new CalculationModule(inputData);
            double sum = module.calculateInterestLeftToPaySum();
            String roundedSum = BigDecimal.valueOf(sum).setScale(2,  RoundingMode.HALF_EVEN).toString();
            InformationDialog.show("The Sum of all future interest payments = " + roundedSum + " $");

        } catch (NumberFormatException | NullPointerException e) {
            ErrorDialog.show("Please make sure your input data is full and correct.");
        } catch (DateTimeParseException e) {
            ErrorDialog.show("Please make sure your date input follows the format: \"DD.MM.YYYY\".");
        }
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

}