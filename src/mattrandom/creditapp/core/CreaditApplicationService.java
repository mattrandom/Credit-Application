package mattrandom.creditapp.core;

import mattrandom.creditapp.core.model.CreditApplication;
import mattrandom.creditapp.core.model.Person;

import java.math.BigDecimal;

public class CreaditApplicationService {

    public String getDecision(CreditApplication creditApplication) {
        Person person = creditApplication.getPerson();
        PersonScoringCalculator calculator = new PersonScoringCalculator();
        String decision;
        int scoring = calculator.calculate(person);

        if (scoring < 300) {
            decision = "Sorry " + person.getPersonalData().getName() + " " + person.getPersonalData().getLastName() + ", decision is negative.";
        } else if (scoring <= 400) {
            decision = "Sorry, " + person.getPersonalData().getName() + " " + person.getPersonalData().getLastName() + ", bank requires additional documents. Our Consultant will contact you.";
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
                decision = "Congratulations, " + person.getPersonalData().getName() + " " + person.getPersonalData().getLastName() + ", decision is positive.";
            } else {
                BigDecimal roundedCreditRate = new BigDecimal(creditRate).setScale(2);
                decision = "Sorry, " + person.getPersonalData().getName() + " " + person.getPersonalData().getLastName() + ", decision is negative. Bank can borrow only " + roundedCreditRate;
            }
        }
        return decision;
    }
}
