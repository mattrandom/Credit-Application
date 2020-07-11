package mattrandom.creditapp;

import mattrandom.creditapp.client.ConsoleReader;
import mattrandom.creditapp.core.*;
import mattrandom.creditapp.core.model.CreditApplication;
import mattrandom.creditapp.core.scoring.EducationCalculator;
import mattrandom.creditapp.core.scoring.IncomeCalculator;
import mattrandom.creditapp.core.scoring.MaritalStatusCalculator;

public class Main {

    public static void main(String[] args) {
        NaturalPersonScoringCalculator naturalPersonScoringCalculator = new NaturalPersonScoringCalculator(new IncomeCalculator(), new MaritalStatusCalculator(), new EducationCalculator());
        SelfEmployedScoringCalculator selfEmployedScoringCalculator = new SelfEmployedScoringCalculator(new IncomeCalculator(), new MaritalStatusCalculator(), new EducationCalculator());
        PersonScoringCalculatorFactory personScoringCalculatorFactory = new PersonScoringCalculatorFactory(naturalPersonScoringCalculator, selfEmployedScoringCalculator);
        CreaditApplicationService service = new CreaditApplicationService(personScoringCalculatorFactory, new CreditRatingCalculator());
        CreditApplication creditApplication = new ConsoleReader().readInputParameters();
        CreditApplicationDecision decision = service.getDecision(creditApplication);
        System.out.println(decision.getDesisionString());

    }
}
