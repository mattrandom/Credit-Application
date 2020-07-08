package mattrandom.creditapp.core;

import mattrandom.creditapp.core.model.CreditApplication;
import mattrandom.creditapp.core.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.UUID;

import static mattrandom.creditapp.core.Constants.MIN_LOAN_AMOUNT_MORTGAGE;
import static mattrandom.creditapp.core.DecisionType.*;

public class CreaditApplicationService {
    private static final Logger log = LoggerFactory.getLogger(CreaditApplicationService.class);
    private final PersonScoringCalculator personScoringCalculator;
    private final CreditRatingCalculator creditRatingCalculator;

    public CreaditApplicationService(PersonScoringCalculator calculator, CreditRatingCalculator creditRatingCalculator) {
        this.personScoringCalculator = calculator;
        this.creditRatingCalculator = creditRatingCalculator;
    }

    public CreditApplicationDecision getDecision(CreditApplication creditApplication) {
        String id = UUID.randomUUID().toString();
        log.info("Application ID is " + id);
        MDC.put("id", id);

        Person person = creditApplication.getPerson();
        int scoring = personScoringCalculator.calculate(person);
        CreditApplicationDecision decision;

        if (scoring < 300) {
            decision =  new CreditApplicationDecision(NEGATIVE_SCORING, person.getPersonalData(),null, scoring);
        } else if (scoring <= 400) {
            decision =  new CreditApplicationDecision(CONTACT_REQUIRED, person.getPersonalData(), null, scoring);
        } else {
            double creditRate = creditRatingCalculator.calculate(creditApplication);
            if (creditRate >= creditApplication.getPurposeOfLoan().getAmount()) {
                if (creditApplication.getPurposeOfLoan().getAmount() < MIN_LOAN_AMOUNT_MORTGAGE) {
                    decision = new CreditApplicationDecision(NEGATIVE_REQUIREMENTS_NOT_MET, person.getPersonalData(), creditRate, scoring);
                } else {
                    decision = new CreditApplicationDecision(POSITIVE, person.getPersonalData(), creditRate, scoring);
                }
            } else {
                decision =  new CreditApplicationDecision(NEGATIVE_RATING, person.getPersonalData(), creditRate, scoring);
            }
        }
        log.info("Decision = " + decision.getType());
        return decision;
    }

}
