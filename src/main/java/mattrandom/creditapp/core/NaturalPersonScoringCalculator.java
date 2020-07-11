package mattrandom.creditapp.core;

import mattrandom.creditapp.core.model.Person;
import mattrandom.creditapp.core.scoring.EducationCalculator;
import mattrandom.creditapp.core.scoring.IncomeCalculator;
import mattrandom.creditapp.core.scoring.MaritalStatusCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NaturalPersonScoringCalculator extends PersonScoringCalculator {
    private static final Logger log = LoggerFactory.getLogger(NaturalPersonScoringCalculator.class);

    public NaturalPersonScoringCalculator(IncomeCalculator incomeCalculator, MaritalStatusCalculator maritalStatusCalculator, EducationCalculator educationCalculator) {
        super(incomeCalculator, maritalStatusCalculator, educationCalculator);
    }

    @Override
    protected int addAdditionalPoints(Person person) {
        return 0;
    }
}
