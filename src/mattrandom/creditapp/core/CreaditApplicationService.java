package mattrandom.creditapp.core;

public class CreaditApplicationService {

    public String getDecision(Person person) {
        PersonScoringCalculator calculator = new PersonScoringCalculator();
        String decisionNegative = "Sorry " + person.getName() + " " + person.getLastName() + ", decision is negative.";
        String decisionPositive = "Congratulations, " + person.getName() + " " + person.getLastName() + ", decision is positive.";
        return calculator.calculate(person) < 300 ? decisionNegative : decisionPositive;
    }
}
