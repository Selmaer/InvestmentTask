package com.simcorp.investment_task.data;

public class Payment {
    private final double PRINCIPAL_PAYMENT;
    private final double INTEREST_PAYMENT;

    public Payment(double PRINCIPAL_PAYMENT, double INTEREST_PAYMENT) {
        this.PRINCIPAL_PAYMENT = PRINCIPAL_PAYMENT;
        this.INTEREST_PAYMENT = INTEREST_PAYMENT;
    }

    public double getINTEREST_PAYMENT() {
        return INTEREST_PAYMENT;
    }

    public String toString() {
        return "[Principal: " + PRINCIPAL_PAYMENT + "; Interest: " + INTEREST_PAYMENT + "]";
    }
}
