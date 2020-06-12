package mattrandom.creditapp.core;

import mattrandom.creditapp.core.model.CreditApplication;
import mattrandom.creditapp.core.model.Person;

public class CreditRatingCalculator {

    public double calculate(CreditApplication creditApplication) {
        Person person = creditApplication.getPerson();
        double creditRate = person.getIncomePerFamilyMember() * 12 * creditApplication.getPurposeOfLoan().getPeriod();
        switch (creditApplication.getPurposeOfLoan().getPurposeOfLoanType()) {
            case MORTGAGE:
                creditRate *= Constants.MORTGAGE_LOAN_RATE;
                break;
            case PERSONAL_LOAN:
                creditRate *= Constants.PERSONAL_LOAN_LOAN_RATE;
                break;
        }
        return creditRate;
    }
}
