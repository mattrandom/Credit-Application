package mattrandom.creditapp;

import mattrandom.creditapp.client.ConsoleReader;
import mattrandom.creditapp.core.CreaditApplicationService;
import mattrandom.creditapp.core.Person;

public class Main {

    public static void main(String[] args) {
        CreaditApplicationService service = new CreaditApplicationService();
        Person person = new ConsoleReader().readInputParameters();

        String decision = service.getDecision(person);
        System.out.println(decision);




    }
}
