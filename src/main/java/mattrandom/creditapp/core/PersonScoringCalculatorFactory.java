package mattrandom.creditapp.core;

import mattrandom.creditapp.core.model.NaturalPerson;
import mattrandom.creditapp.core.model.Person;
import mattrandom.creditapp.core.model.SelfEmployed;
import mattrandom.creditapp.core.scoring.*;

public class PersonScoringCalculatorFactory {
    private final SelfEmployedScoringCalculator selfEmployedScoringCalculator;
    private final EducationCalculator educationCalculator;
    private final MaritalStatusCalculator maritalStatusCalculator;
    private final IncomeCalculator incomeCalculator;

    public PersonScoringCalculatorFactory(SelfEmployedScoringCalculator selfEmployedScoringCalculator, EducationCalculator educationCalculator, MaritalStatusCalculator maritalStatusCalculator, IncomeCalculator incomeCalculator) {
        this.selfEmployedScoringCalculator = selfEmployedScoringCalculator;
        this.educationCalculator = educationCalculator;
        this.maritalStatusCalculator = maritalStatusCalculator;
        this.incomeCalculator = incomeCalculator;
    }

    public PersonCalculator getCalculator(Person person) {
        if (person instanceof NaturalPerson) {
            return new CompoundScoringCalculator(educationCalculator, maritalStatusCalculator, incomeCalculator);
        } else if (person instanceof SelfEmployed) {
            return new CompoundScoringCalculator(educationCalculator, maritalStatusCalculator, incomeCalculator, selfEmployedScoringCalculator);
        }
        return null;
    }
}