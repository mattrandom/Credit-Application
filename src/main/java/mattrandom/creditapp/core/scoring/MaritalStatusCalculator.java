package mattrandom.creditapp.core.scoring;

import mattrandom.creditapp.core.model.CreditApplication;
import mattrandom.creditapp.core.model.MaritalStatus;
import mattrandom.creditapp.core.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaritalStatusCalculator implements ScoringCalculator {

    private static final Logger log = LoggerFactory.getLogger(MaritalStatusCalculator.class);

    @Override
    public int calculate(Person person) {
        MaritalStatus maritalStatus = person.getPersonalData().getMaritalStatus();
        int pointsForMaritalStatus = maritalStatus.getScoringPoints();
        log.info("Marital status = " + maritalStatus + ScoringUtils.getPointsString(pointsForMaritalStatus));
        return pointsForMaritalStatus;
    }
}
