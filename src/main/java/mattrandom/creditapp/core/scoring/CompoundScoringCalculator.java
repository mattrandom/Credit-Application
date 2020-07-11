package mattrandom.creditapp.core.scoring;

import mattrandom.creditapp.core.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompoundScoringCalculator implements PersonCalculator {
    private static final Logger log = LoggerFactory.getLogger(CompoundScoringCalculator.class);
    private final PersonCalculator[] calculators;

    public CompoundScoringCalculator(PersonCalculator... calculators) {
        this.calculators = calculators;
    }

    @Override
    public int calculate(Person person) {
        int scoring = 0;

        for (PersonCalculator calculator : calculators) {
            scoring += calculator.calculate(person);
        }

        log.info("Calculated scoring = " + scoring + " points");
        return scoring;
    }
}
