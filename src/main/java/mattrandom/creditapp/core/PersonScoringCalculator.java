package mattrandom.creditapp.core;

import mattrandom.creditapp.core.model.Person;
import mattrandom.creditapp.core.scoring.EducationCalculator;
import mattrandom.creditapp.core.scoring.IncomeCalculator;
import mattrandom.creditapp.core.scoring.MaritalStatusCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonScoringCalculator {

    private static final Logger log = LoggerFactory.getLogger(PersonScoringCalculator.class);
    private final IncomeCalculator incomeCalculator;
    private final MaritalStatusCalculator maritalStatusCalculator;
    private final EducationCalculator educationCalculator;

    public PersonScoringCalculator(IncomeCalculator incomeCalculator, MaritalStatusCalculator maritalStatusCalculator,
                                   EducationCalculator educationCalculator) {
        this.incomeCalculator = incomeCalculator;
        this.maritalStatusCalculator = maritalStatusCalculator;
        this.educationCalculator = educationCalculator;
    }

    public int calculate(Person person) {
        int scoring = incomeCalculator.calculate(person) + maritalStatusCalculator.calculate(person) +
                educationCalculator.calculate(person);
        log.info("Calculated scoring = " + scoring + " points");
        return scoring;
    }
}
