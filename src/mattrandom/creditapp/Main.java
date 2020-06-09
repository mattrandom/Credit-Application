package mattrandom.creditapp;

import mattrandom.creditapp.client.ConsoleReader;
import mattrandom.creditapp.core.CreaditApplicationService;
import mattrandom.creditapp.core.model.CreditApplication;

public class Main {

    public static void main(String[] args) {
        CreaditApplicationService service = new CreaditApplicationService();
        CreditApplication creditApplication = new ConsoleReader().readInputParameters();

        String decision = service.getDecision(creditApplication);
        System.out.println(decision);

    }
}
