package mattrandom.creditapp;

import mattrandom.creditapp.client.ConsoleReader;
import mattrandom.creditapp.core.CreaditApplicationService;
import mattrandom.creditapp.core.CreditApplicationDecision;
import mattrandom.creditapp.core.CreditRatingCalculator;
import mattrandom.creditapp.core.PersonScoringCalculator;
import mattrandom.creditapp.core.model.CreditApplication;

public class Main {

    public static void main(String[] args) {
        CreaditApplicationService service = new CreaditApplicationService(new PersonScoringCalculator(), new CreditRatingCalculator());
        CreditApplication creditApplication = new ConsoleReader().readInputParameters();

        CreditApplicationDecision decision = service.getDecision(creditApplication);
        System.out.println(decision.getDesisionString());

    }
}
