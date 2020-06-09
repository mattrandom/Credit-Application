package mattrandom.creditapp.core.model;

public class PurposeOfLoan {
    private final PurposeOfLoanType purposeOfLoanType;
    private final double amount;

    public PurposeOfLoan(PurposeOfLoanType purposeOfLoanType, double amount) {
        this.purposeOfLoanType = purposeOfLoanType;
        this.amount = amount;
    }
}
