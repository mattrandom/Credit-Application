package mattrandom.creditapp.core;

import mattrandom.creditapp.core.model.CreditApplication;
import mattrandom.creditapp.core.model.Person;

import java.math.BigDecimal;

public class CreaditApplicationService {
    private final PersonScoringCalculator calculator;

    public CreaditApplicationService(PersonScoringCalculator calculator) {
        this.calculator = calculator;
    }

    public CreditApplicationDecision getDecision(CreditApplication creditApplication) {
        Person person = creditApplication.getPerson();
        int scoring = calculator.calculate(person);

        if (scoring < 300) {
            return new CreditApplicationDecision(DecisionType.NEGATIVE_SCORING, person.getPersonalData(),null);
        } else if (scoring <= 400) {
            return new CreditApplicationDecision(DecisionType.CONTACT_REQUIRED, person.getPersonalData(), null);
        } else {
            double creditRate = person.getIncomePerFamilyMember() * 12 * creditApplication.getPurposeOfLoan().getPeriod();

            switch (creditApplication.getPurposeOfLoan().getPurposeOfLoanType()) {
                case MORTGAGE:
                    creditRate *= Constants.MORTGAGE_LOAN_RATE;
                    break;
                case PERSONAL_LOAN:
                    creditRate *= Constants.PERSONAL_LOAN_LOAN_RATE;
                    break;
            }
            if (creditRate >= creditApplication.getPurposeOfLoan().getAmount()) {
                return new CreditApplicationDecision(DecisionType.POSITIVE, person.getPersonalData(), creditRate);
            } else {
                BigDecimal roundedCreditRate = new BigDecimal(creditRate).setScale(2);
                return new CreditApplicationDecision(DecisionType.NEGATIVE_RATING, person.getPersonalData(), creditRate);
            }
        }
    }
}
