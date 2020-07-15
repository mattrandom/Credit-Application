package mattrandom.creditapp.core.validation;

import mattrandom.creditapp.core.exception.ValidationException;
import mattrandom.creditapp.core.model.CreditApplication;
import mattrandom.creditapp.core.model.PurposeOfLoan;

public class PurposeOfLoanValidator implements Validator {

    @Override
    public void validate(CreditApplication creditApplication) throws ValidationException {
        final PurposeOfLoan purposeOfLoan = creditApplication.getPurposeOfLoan();

        ValidationUtils.validateNotNull("purposeOfLoanType", purposeOfLoan.getPurposeOfLoanType());
        ValidationUtils.validateMinValue("purposeOfLoanAmount", 0.0, purposeOfLoan.getAmount());
    }
}
