package com.simcorp.investment_task;

import com.simcorp.investment_task.data.InputData;
import com.simcorp.investment_task.data.Payment;
import com.simcorp.investment_task.gui.WarningDialog;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class CalculationModule {
    private final InputData DATA;
    private final int totalNumberOfPayments;

    public CalculationModule(InputData inputData) {
        DATA = inputData;
        totalNumberOfPayments = DATA.getDURATION() * 12;
    }

    public double calculateInterestLeftToPaySum() {

        int numberOfPreviousPayments = calculateNumberOfPreviousPayments();
        List<Payment> paymentList = calculateAllPayments();
        double interestLeftToPaySum = 0;
        for (int i = numberOfPreviousPayments; i <= paymentList.size() - 1; i++) {
            interestLeftToPaySum += paymentList.get(i).getINTEREST_PAYMENT();
        }
        return interestLeftToPaySum;
    }

    /*  I'm not using the Compound Interest formula here to find the monthly interest because
        it is specified in the task that interest amount is calculated only on the outstanding
        principal amount.*/
    private double calculateMonthlyInterest() {
        return DATA.getINTEREST() / (12 * 100);
    }

    /*  I am using Annuity formula to calculate an amount of monthly payment because it is fixed.
        And as the last refund payment should be done in N years, it is Annuity-immediate. */
    private double calculateAnnuityCoefficient() {
        double coefficient =
                (calculateMonthlyInterest() * Math.pow((calculateMonthlyInterest() + 1), totalNumberOfPayments)) /
                (Math.pow((calculateMonthlyInterest() + 1), totalNumberOfPayments) - 1);
        return coefficient;
    }

    private List<Payment> calculateAllPayments() {
        List<Payment> paymentList = new ArrayList<>();
        double creditBody = DATA.getINVESTMENT();
        double monthlyPayment = DATA.getINVESTMENT() * calculateAnnuityCoefficient();
        for (int i = 0; i < totalNumberOfPayments; i++) {
            double interestPayment = creditBody * calculateMonthlyInterest();
            double principalPayment = monthlyPayment - interestPayment;
            paymentList.add(new Payment(principalPayment, interestPayment));
            creditBody -= principalPayment;
        }
        return paymentList;
    }

    private int calculateNumberOfPreviousPayments() {
        int numberOfPreviousPayments;
        if (DATA.getAGREEMENT_DATE().isAfter(DATA.getCALCULATION_DATE())) {
            WarningDialog.show("Your Calculation date is before the Agreement date.\nThe Sum of all future " +
                    "interest payments will be equal\nto total interest amount from investment.");
            numberOfPreviousPayments = 0;
        } else if (DATA.getAGREEMENT_DATE().plusYears(DATA.getDURATION()).isBefore(DATA.getCALCULATION_DATE())){
            WarningDialog.show("Your Calculation date is after the last refund payment date.\nThe Sum of all " +
                    "future interest payments will be zero.");
            numberOfPreviousPayments = totalNumberOfPayments;
        } else {
            Period agreementLastingPeriod = Period.between(DATA.getAGREEMENT_DATE(), DATA.getCALCULATION_DATE());
            numberOfPreviousPayments = (int) agreementLastingPeriod.toTotalMonths();
        }
        return numberOfPreviousPayments;
    }

}
