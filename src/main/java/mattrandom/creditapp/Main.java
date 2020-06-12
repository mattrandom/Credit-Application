package mattrandom.creditapp;

import mattrandom.creditapp.client.ConsoleReader;
import mattrandom.creditapp.core.CreaditApplicationService;
import mattrandom.creditapp.core.CreditApplicationDecision;
import mattrandom.creditapp.core.CreditRatingCalculator;
import mattrandom.creditapp.core.PersonScoringCalculator;
import mattrandom.creditapp.core.model.CreditApplication;
import mattrandom.creditapp.core.scoring.EducationCalculator;
import mattrandom.creditapp.core.scoring.IncomeCalculator;
import mattrandom.creditapp.core.scoring.MaritalStatusCalculator;

public class Main {

    public static void main(String[] args) {
        PersonScoringCalculator calculator = new PersonScoringCalculator(new IncomeCalculator(), new MaritalStatusCalculator(), new EducationCalculator());
        CreaditApplicationService service = new CreaditApplicationService(calculator, new CreditRatingCalculator());
        CreditApplication creditApplication = new ConsoleReader().readInputParameters();

        CreditApplicationDecision decision = service.getDecision(creditApplication);
        System.out.println(decision.getDesisionString());

    }
}
