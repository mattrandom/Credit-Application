package mattrandom.creditapp.core;

import mattrandom.creditapp.core.model.Person;

public class CreaditApplicationService {

    public String getDecision(Person person) {
        PersonScoringCalculator calculator = new PersonScoringCalculator();
        String decisionNegative = "Sorry " + person.getPersonalData().getName() + " " + person.getPersonalData().getLastName() + ", decision is negative.";
        String decisionPositive = "Congratulations, " + person.getPersonalData().getName() + " " + person.getPersonalData().getLastName() + ", decision is positive.";
        return calculator.calculate(person) < 300 ? decisionNegative : decisionPositive;
    }
}
