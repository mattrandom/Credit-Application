package mattrandom.creditapp.client;

import mattrandom.creditapp.core.Person;

import java.util.Scanner;

public class ConsoleReader {

    public Person readInputParameters() {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter your name: ");
        String name = in.next();

        System.out.println("Enter your last name: ");
        String lastName = in.next();

        System.out.println("Enter your mothers maiden name: ");
        String mothersMaidenName = in.next();

        System.out.println("Enter total monthly income in PLN: ");
        double totalMonthlyIncomeInPln = in.nextDouble();

        System.out.println("Are you married: ");
        boolean married = in.nextBoolean();

        System.out.println("Enter number of family dependants (including applicant): ");
        int numOfFamilyDependants = in.nextInt();


        return new Person(name, lastName, mothersMaidenName, totalMonthlyIncomeInPln, married, numOfFamilyDependants);
    }
}
