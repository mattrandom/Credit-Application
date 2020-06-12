package mattrandom.creditapp.core;

import mattrandom.creditapp.core.model.CreditApplication;
import mattrandom.creditapp.core.model.Person;

import java.math.BigDecimal;

public class CreaditApplicationService {
    private final PersonScoringCalculator personScoringCalculator;
    private final CreditRatingCalculator creditRatingCalculator;

    public CreaditApplicationService(PersonScoringCalculator calculator, CreditRatingCalculator creditRatingCalculator) {
        this.personScoringCalculator = calculator;
        this.creditRatingCalculator = creditRatingCalculator;
    }

    public CreditApplicationDecision getDecision(CreditApplication creditApplication) {
        Person person = creditApplication.getPerson();
        int scoring = personScoringCalculator.calculate(person);

        if (scoring < 300) {
            return new CreditApplicationDecision(DecisionType.NEGATIVE_SCORING, person.getPersonalData(),null);
        } else if (scoring <= 400) {
            return new CreditApplicationDecision(DecisionType.CONTACT_REQUIRED, person.getPersonalData(), null);
        } else {
            double creditRate = creditRatingCalculator.calculate(creditApplication);
            if (creditRate >= creditApplication.getPurposeOfLoan().getAmount()) {
                return new CreditApplicationDecision(DecisionType.POSITIVE, person.getPersonalData(), creditRate);
            } else {
                BigDecimal roundedCreditRate = new BigDecimal(creditRate).setScale(2);
                return new CreditApplicationDecision(DecisionType.NEGATIVE_RATING, person.getPersonalData(), creditRate);
            }
        }
    }

}
