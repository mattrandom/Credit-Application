package mattrandom.creditapp;

import mattrandom.creditapp.client.CreditApplicationReader;
import mattrandom.creditapp.client.DummyCreditApplicationReader;
import mattrandom.creditapp.core.*;
import mattrandom.creditapp.core.validation.*;
import mattrandom.creditapp.core.validation.reflection.*;
import mattrandom.creditapp.di.ClassInitializer;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        CreditApplicationReader reader = new DummyCreditApplicationReader();

        List<ClassAnnotationProcessor> classProcessors = List.of(new ExactlyOneNotNullAnnotationProcessor());
        List<FieldAnnotationProcessor> fieldProcessors = List.of(new NotNullAnnotationProcessor(), new RegexAnnotationProcessor());

        ObjectValidator objectValidator = new ObjectValidator(fieldProcessors, classProcessors);
        CompoundPostValidator compoundPostValidator = new CompoundPostValidator(new PurposeOfLoanPostValidator(), new ExpensesPostValidator());

        ClassInitializer classInitializer = new ClassInitializer();
        classInitializer.registerInstance(compoundPostValidator);
        classInitializer.registerInstance(objectValidator);

        CreditApplicationManager manager = (CreditApplicationManager) classInitializer.createInstance(CreditApplicationManager.class);

        manager.add(reader.read());

        manager.startProcessing();

    }
}
