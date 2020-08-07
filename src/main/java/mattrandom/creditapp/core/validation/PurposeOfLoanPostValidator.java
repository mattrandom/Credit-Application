package mattrandom.creditapp.core.validation;

import mattrandom.creditapp.core.exception.RequirementNotMetCause;
import mattrandom.creditapp.core.exception.RequirementNotMetException;
import mattrandom.creditapp.core.model.CreditApplication;

import static mattrandom.creditapp.core.Constants.MIN_LOAN_AMOUNT_MORTGAGE;

public class PurposeOfLoanPostValidator implements PostValidator {

    @Override
    public void validate(CreditApplication creditApplication, int scoring, double rating) throws RequirementNotMetException {
        if (creditApplication.getPurposeOfLoan().getAmount() < MIN_LOAN_AMOUNT_MORTGAGE) {
            throw new RequirementNotMetException(RequirementNotMetCause.TOO_LOW_LOAN_AMOUNT);
        }
    }
}
