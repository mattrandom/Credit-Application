package mattrandom.creditapp;

import mattrandom.creditapp.client.CreditApplicationReader;
import mattrandom.creditapp.client.DummyCreditApplicationReader;
import mattrandom.creditapp.core.*;
import mattrandom.creditapp.core.scoring.EducationCalculator;
import mattrandom.creditapp.core.scoring.GuarantorsCalculator;
import mattrandom.creditapp.core.scoring.IncomeCalculator;
import mattrandom.creditapp.core.scoring.MaritalStatusCalculator;
import mattrandom.creditapp.core.validation.*;
import mattrandom.creditapp.core.validation.reflection.*;

import java.util.Set;

public class Main {

    public static void main(String[] args) {
        CreditApplicationReader reader = new DummyCreditApplicationReader();
        EducationCalculator educationCalculator = new EducationCalculator();
        MaritalStatusCalculator maritalStatusCalculator = new MaritalStatusCalculator();
        IncomeCalculator incomeCalculator = new IncomeCalculator();
        SelfEmployedScoringCalculator selfEmployedScoringCalculator = new SelfEmployedScoringCalculator();
        GuarantorsCalculator guarantorsCalculator = new GuarantorsCalculator();
        PersonScoringCalculatorFactory personScoringCalculatorFactory = new PersonScoringCalculatorFactory(selfEmployedScoringCalculator, educationCalculator, maritalStatusCalculator, incomeCalculator, guarantorsCalculator);
        Set<ClassAnnotationProcessor> classProcessors = Set.of(new ExactlyOneNotNullAnnotationProcessor());
        Set<FieldAnnotationProcessor> fieldProcessors = Set.of(new NotNullAnnotationProcessor(), new RegexAnnotationProcessor());
        ObjectValidator objectValidator = new ObjectValidator(fieldProcessors, classProcessors);
        CreditApplicationValidator creditApplicationValidator = new CreditApplicationValidator(objectValidator);
        CompoundPostValidator compoundPostValidator = new CompoundPostValidator(new PurposeOfLoanPostValidator(), new ExpensesPostValidator());
        CreditApplicationService service = new CreditApplicationService(personScoringCalculatorFactory, new CreditRatingCalculator(), creditApplicationValidator, compoundPostValidator);
        CreditApplicationManager manager = new CreditApplicationManager(service);

        manager.add(reader.read());

        manager.startProcessing();

    }
}
