package mattrandom.creditapp.core.model;

import mattrandom.creditapp.core.annotation.NotNull;

import java.io.Serializable;

public class PurposeOfLoan implements Serializable {
    public static final long serialVersionUID = 1L;
    @NotNull
    private final PurposeOfLoanType purposeOfLoanType;
    private final double amount;
    private final int period;

    public PurposeOfLoan(PurposeOfLoanType purposeOfLoanType, double amount, int period) {
        this.purposeOfLoanType = purposeOfLoanType;
        this.amount = amount;
        this.period = period;
    }

    public PurposeOfLoanType getPurposeOfLoanType() {
        return purposeOfLoanType;
    }

    public double getAmount() {
        return amount;
    }

    public int getPeriod() {
        return period;
    }
}
