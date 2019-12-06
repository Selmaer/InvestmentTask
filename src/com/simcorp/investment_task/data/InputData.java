package com.simcorp.investment_task.data;

import java.time.LocalDate;

public class InputData {
    private final int INVESTMENT;
    private final double INTEREST;
    private final int DURATION;
    private final LocalDate AGREEMENT_DATE;
    private final LocalDate CALCULATION_DATE;

    public InputData(int investment, LocalDate agreementDate, LocalDate calculationDate,
                             double interest, int duration) {
        this.INVESTMENT = investment;
        this.INTEREST = interest;
        this.DURATION = duration;
        this.AGREEMENT_DATE = agreementDate;
        this.CALCULATION_DATE = calculationDate;
    }

    public int getINVESTMENT() {
        return INVESTMENT;
    }

    public double getINTEREST() {
        return INTEREST;
    }

    public int getDURATION() {
        return DURATION;
    }

    public LocalDate getAGREEMENT_DATE() {
        return AGREEMENT_DATE;
    }

    public LocalDate getCALCULATION_DATE() {
        return CALCULATION_DATE;
    }
}
