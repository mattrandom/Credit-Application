package mattrandom.creditapp;

import mattrandom.creditapp.client.CreditApplicationReader;
import mattrandom.creditapp.client.DummyCreditApplicationReader;
import mattrandom.creditapp.core.*;
import mattrandom.creditapp.core.scoring.BikScoringCalculator;
import mattrandom.creditapp.core.validation.*;
import mattrandom.creditapp.core.validation.reflection.*;
import mattrandom.creditapp.di.ClassInitializer;
import mattrandom.creditapp.integration.BikApiAdapter;

import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class Main {
    static {
        TimeZone.setDefault(TimeZone.getTimeZone(Constants.DEFAULT_SYSTEM_ZONE_ID));
        Locale.setDefault(Constants.DEFAULT_SYSTEM_LOCALE);
    }

    public static void main(String[] args) throws Exception {
        CreditApplicationReader reader = new DummyCreditApplicationReader();

        List<ClassAnnotationProcessor> classProcessors = List.of(new ExactlyOneNotNullAnnotationProcessor());
        List<FieldAnnotationProcessor> fieldProcessors = List.of(new NotNullAnnotationProcessor(), new RegexAnnotationProcessor());

        ObjectValidator objectValidator = new ObjectValidator(fieldProcessors, classProcessors);
        CompoundPostValidator compoundPostValidator = new CompoundPostValidator(new PurposeOfLoanPostValidator(), new ExpensesPostValidator());

        ClassInitializer classInitializer = new ClassInitializer();
        classInitializer.registerInstance(compoundPostValidator);
        classInitializer.registerInstance(objectValidator);
        classInitializer.registerInstance(new BikScoringCalculator(new BikApiAdapter()));

        CreditApplicationManager manager = (CreditApplicationManager) classInitializer.createInstance(CreditApplicationManager.class);
        if (args != null && args.length > 0) {
            String id = args[0];
            manager.loadApplication(id);
        } else {
            manager.add(reader.read());
            manager.startProcessing();
        }
    }
}
