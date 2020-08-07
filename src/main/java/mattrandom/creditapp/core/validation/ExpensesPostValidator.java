package mattrandom.creditapp.core.validation;

import mattrandom.creditapp.core.exception.RequirementNotMetCause;
import mattrandom.creditapp.core.exception.RequirementNotMetException;
import mattrandom.creditapp.core.model.CreditApplication;
import mattrandom.creditapp.core.model.ExpenseType;

public class ExpensesPostValidator implements PostValidator {

    @Override
    public void validate(CreditApplication creditApplication, int scoring, double rating) throws RequirementNotMetException {
        double balance = creditApplication.getPerson().getBalance();
        double personalExpenses = creditApplication.getPerson().getFinanceData().getSumOfExpenses(ExpenseType.PERSONAL);

        double percentage = personalExpenses * 100 / balance;

        if (percentage > 40) {
            throw new RequirementNotMetException(RequirementNotMetCause.TOO_HIGH_PERSONAL_EXPENSES);
        }
    }
}
