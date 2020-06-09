package pl.javaskills.creditapp;

import pl.javaskills.creditapp.client.ConsoleReader;
import pl.javaskills.creditapp.core.Person;

public class Main {

    public static void main(String[] args) {
        Person person = new ConsoleReader().readInputParameters();

        System.out.println("Hello, " + person.getName() + " " + person.getLastName() + "!");
    }
}
