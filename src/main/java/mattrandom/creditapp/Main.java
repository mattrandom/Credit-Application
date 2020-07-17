package mattrandom.creditapp;

import mattrandom.creditapp.client.CreditApplicationReader;
import mattrandom.creditapp.client.DummyCreditApplicationReader;
import mattrandom.creditapp.core.*;
import mattrandom.creditapp.core.model.CreditApplication;
import mattrandom.creditapp.core.scoring.EducationCalculator;
import mattrandom.creditapp.core.scoring.IncomeCalculator;
import mattrandom.creditapp.core.scoring.MaritalStatusCalculator;
import mattrandom.creditapp.core.validation.CreditApplicationValidator;
import mattrandom.creditapp.core.validation.PersonValidator;
import mattrandom.creditapp.core.validation.PersonalDataValidator;
import mattrandom.creditapp.core.validation.PurposeOfLoanValidator;

public class Main {

    public static void main(String[] args) {
        CreditApplicationReader reader = new DummyCreditApplicationReader();
        EducationCalculator educationCalculator = new EducationCalculator();
        MaritalStatusCalculator maritalStatusCalculator = new MaritalStatusCalculator();
        IncomeCalculator incomeCalculator = new IncomeCalculator();
        SelfEmployedScoringCalculator selfEmployedScoringCalculator = new SelfEmployedScoringCalculator();
        PersonScoringCalculatorFactory personScoringCalculatorFactory = new PersonScoringCalculatorFactory(selfEmployedScoringCalculator, educationCalculator, maritalStatusCalculator, incomeCalculator);

        CreditApplicationValidator creditApplicationValidator = new CreditApplicationValidator(new PersonValidator(new PersonalDataValidator()), new PurposeOfLoanValidator());
        CreditApplicationService service = new CreditApplicationService(personScoringCalculatorFactory, new CreditRatingCalculator(), creditApplicationValidator);
        CreditApplicationManager manager = new CreditApplicationManager(service);

        manager.add(reader.read());
        manager.add(reader.read());
        manager.add(reader.read());
        manager.add(reader.read());

        manager.startProcessing();

    }
}
