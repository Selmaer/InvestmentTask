package com.simcorp.investment_task.gui;

import com.simcorp.investment_task.data.InputData;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;

public class MainWindow {
    private Stage primaryStage;
    private TextField investmentField;
    private DatePicker agreementDatePicker;
    private DatePicker calculationDatePicker;
    private TextField interestField;
    private TextField durationField;
    private Button calculateSumButton;

    public MainWindow(Stage primaryStage) {
        this.primaryStage = primaryStage;
        setMainWindow();
    }

    public Button getCalculateSumButton() {
        return calculateSumButton;
    }

    public InputData getInputData() {
        setDateFromInputText(agreementDatePicker);
        setDateFromInputText(calculationDatePicker);

        int investment = Integer.parseInt(investmentField.getText());
        LocalDate agreementDate = agreementDatePicker.getValue();
        LocalDate calculationDate = calculationDatePicker.getValue();
        double interest = Double.parseDouble(interestField.getText());
        int duration = Integer.parseInt(durationField.getText());

        return new InputData(investment, agreementDate, calculationDate,
                interest, duration);
    }

    private void setDateFromInputText(DatePicker datePicker) {
        datePicker.setValue(datePicker.getConverter().fromString(datePicker.getEditor().getText()));
    }

    private void setMainWindow() {
        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(5);
        root.setVgap(5);

        setElements(root);

        Scene scene = new Scene(root, 333, 200);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Investment Task");

        primaryStage.show();
    }

    private void setElements(GridPane root) {
        investmentField = new TextField();
        root.add(new Label("Investment ($):"), 0, 0);
        root.add(investmentField, 1, 0);

        agreementDatePicker = new DatePicker();
        root.add(new Label("Agreement date:"), 0, 1);
        root.add(agreementDatePicker, 1, 1);

        calculationDatePicker = new DatePicker();
        calculationDatePicker.setValue(LocalDate.now());
        root.add(new Label("Calculation date:"), 0, 2);
        root.add(calculationDatePicker, 1, 2);

        interestField = new TextField();
        root.add(new Label("Interest rate (% per year):"), 0, 4);
        root.add(interestField, 1, 4);

        durationField = new TextField();
        root.add(new Label("Investment duration (years):"), 0, 5);
        root.add(durationField, 1, 5);

        calculateSumButton = new Button("Calculate Sum");
        calculateSumButton.setMinSize(313, 25);
        root.add(calculateSumButton, 0, 6, 2, 1);
    }
}
