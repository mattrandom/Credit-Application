package mattrandom.creditapp.core;

import mattrandom.creditapp.core.model.Person;
import mattrandom.creditapp.core.model.SelfEmployed;
import mattrandom.creditapp.core.scoring.EducationCalculator;
import mattrandom.creditapp.core.scoring.IncomeCalculator;
import mattrandom.creditapp.core.scoring.MaritalStatusCalculator;
import mattrandom.creditapp.core.scoring.ScoringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelfEmployedScoringCalculator extends PersonScoringCalculator {
    private static final Logger log = LoggerFactory.getLogger(SelfEmployedScoringCalculator.class);

    public SelfEmployedScoringCalculator(IncomeCalculator incomeCalculator, MaritalStatusCalculator maritalStatusCalculator, EducationCalculator educationCalculator) {
        super(incomeCalculator, maritalStatusCalculator, educationCalculator);
    }

    @Override
    protected int addAdditionalPoints(Person person) {
        if (person instanceof SelfEmployed) {
            SelfEmployed selfEmployed = (SelfEmployed) person;
            if (selfEmployed.getYearsSinceFounded() < 2) {
                log.info("Years since founded = " + selfEmployed.getYearsSinceFounded() + ScoringUtils.getPointsString(-200));
                return -200;
            }
        }
        return 0;
    }
}
