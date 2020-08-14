package mattrandom.creditapp.core;

import mattrandom.creditapp.core.exception.RequirementNotMetException;
import mattrandom.creditapp.core.exception.ValidationException;
import mattrandom.creditapp.core.model.CreditApplication;
import mattrandom.creditapp.core.model.Person;
import mattrandom.creditapp.core.validation.CompoundPostValidator;
import mattrandom.creditapp.core.validation.CreditApplicationValidator;
import mattrandom.creditapp.di.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;

import static mattrandom.creditapp.core.Constants.MIN_LOAN_AMOUNT_MORTGAGE;
import static mattrandom.creditapp.core.DecisionType.*;

public class CreditApplicationService {
    private static final Logger log = LoggerFactory.getLogger(CreditApplicationService.class);

    @Inject
    private PersonScoringCalculatorFactory personScoringCalculatorFactory;

    @Inject
    private CreditRatingCalculator creditRatingCalculator;

    @Inject
    private CreditApplicationValidator creditApplicationValidator;

    @Inject
    private CompoundPostValidator compoundPostValidator;

    public CreditApplicationService(PersonScoringCalculatorFactory personScoringCalculatorFactory, CreditRatingCalculator creditRatingCalculator, CreditApplicationValidator creditApplicationValidator, CompoundPostValidator compoundPostValidator) {
        this.personScoringCalculatorFactory = personScoringCalculatorFactory;
        this.creditRatingCalculator = creditRatingCalculator;
        this.creditApplicationValidator = creditApplicationValidator;
        this.compoundPostValidator = compoundPostValidator;
    }

    public CreditApplicationService() {
    }

    public CreditApplicationDecision getDecision(CreditApplication creditApplication) {
        String id = creditApplication.getId().toString();
        MDC.put("id", id);
        Instant start = Instant.now();

        try {
            Person person = creditApplication.getPerson();
            // Step 1
            creditApplicationValidator.validate(creditApplication);
            // Step 2
            int scoring = personScoringCalculatorFactory.getCalculator(person).calculate(creditApplication);
            // Step 3
            double creditRate = creditRatingCalculator.calculate(creditApplication);
            // Step 4
            try {
                compoundPostValidator.validate(creditApplication, scoring, creditRate);
            } catch (RequirementNotMetException reqEx) {
                return new CreditApplicationDecision(NEGATIVE_REQUIREMENTS_NOT_MET, person.getPersonalData(), creditRate, scoring, reqEx.getRequirementNotMetCause());
            }
            CreditApplicationDecision decision = getCreditApplicationDecision(creditApplication, person, scoring, creditRate);
            log.info("Decision = " + decision.getType());
            return decision;
        } catch (ValidationException validationException) {
            log.error(validationException.getMessage());
            throw new IllegalStateException();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new IllegalStateException();
        } finally {
            long ms1 = Duration.between(start, Instant.now()).toMillis();
            long ms2 = Duration.between(creditApplication.getCreationDateClientZone(), ZonedDateTime.now(creditApplication.getClientTimeZone())).toMillis();
            log.info("Application processing is finished. It took {}/{} ms", ms1, ms2);
        }
    }

    private CreditApplicationDecision getCreditApplicationDecision(CreditApplication creditApplication, Person person, int scoring, double creditRate) {
        CreditApplicationDecision decision;
        if (scoring < 300) {
            decision = new CreditApplicationDecision(NEGATIVE_SCORING, person.getPersonalData(), creditRate, scoring);
        } else if (scoring <= 400) {
            decision = new CreditApplicationDecision(CONTACT_REQUIRED, person.getPersonalData(), creditRate, scoring);
        } else {
            if (creditRate >= creditApplication.getPurposeOfLoan().getAmount()) {
                decision = new CreditApplicationDecision(POSITIVE, person.getPersonalData(), creditRate, scoring);
            } else {
                decision = new CreditApplicationDecision(NEGATIVE_RATING, person.getPersonalData(), creditRate, scoring);
            }
        }
        return decision;
    }
}
