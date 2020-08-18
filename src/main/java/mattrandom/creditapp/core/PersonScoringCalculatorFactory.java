package mattrandom.creditapp.core;

import mattrandom.creditapp.core.model.NaturalPerson;
import mattrandom.creditapp.core.model.Person;
import mattrandom.creditapp.core.model.SelfEmployed;
import mattrandom.creditapp.core.scoring.*;
import mattrandom.creditapp.di.Inject;

public class PersonScoringCalculatorFactory {

    @Inject
    private SelfEmployedScoringCalculator selfEmployedScoringCalculator;

    @Inject
    private EducationCalculator educationCalculator;

    @Inject
    private MaritalStatusCalculator maritalStatusCalculator;

    @Inject
    private IncomeCalculator incomeCalculator;

    @Inject
    private GuarantorsCalculator guarantorsCalculator;

    @Inject
    private BikScoringCalculator bikScoringCalculator;

    public PersonScoringCalculatorFactory(SelfEmployedScoringCalculator selfEmployedScoringCalculator, EducationCalculator educationCalculator, MaritalStatusCalculator maritalStatusCalculator,
                                          IncomeCalculator incomeCalculator, GuarantorsCalculator guarantorsCalculator, BikScoringCalculator bikScoringCalculator) {
        this.selfEmployedScoringCalculator = selfEmployedScoringCalculator;
        this.educationCalculator = educationCalculator;
        this.maritalStatusCalculator = maritalStatusCalculator;
        this.incomeCalculator = incomeCalculator;
        this.guarantorsCalculator = guarantorsCalculator;
        this.bikScoringCalculator = bikScoringCalculator;
    }

    public PersonScoringCalculatorFactory() {
    }

    public ScoringCalculator getCalculator(Person person) {
        if (person instanceof NaturalPerson) {
            return new CompoundScoringCalculator(bikScoringCalculator, educationCalculator, maritalStatusCalculator, incomeCalculator, guarantorsCalculator);
        } else if (person instanceof SelfEmployed) {
            return new CompoundScoringCalculator(bikScoringCalculator, educationCalculator, maritalStatusCalculator, incomeCalculator, selfEmployedScoringCalculator, guarantorsCalculator);
        }
        return null;
    }
}
