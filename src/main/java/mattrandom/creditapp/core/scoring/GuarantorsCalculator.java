package mattrandom.creditapp.core.scoring;

import mattrandom.creditapp.core.model.CreditApplication;
import mattrandom.creditapp.core.model.Guarantor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GuarantorsCalculator implements ScoringCalculator {
    private static final Logger log = LoggerFactory.getLogger(GuarantorsCalculator.class);

    @Override
    public int calculate(CreditApplication creditApplication) {
        int scoringAgeUnder40 = 0;
        int scoringOthers = 0;

        for (Guarantor g : creditApplication.getGuarantors()) {
            if (g.getAge() < 40) {
                scoringAgeUnder40 += 50;
            } else {
                scoringOthers += 25;
            }
        }

        if (scoringAgeUnder40 > 0) {
            log.info("Extra points for guarantors under the age of 40 = " + scoringAgeUnder40 + ". " + ScoringUtils.getPointsString(scoringAgeUnder40));
        }

        if (scoringOthers > 0) {
            log.info("Extra points for other guarantors = " + scoringOthers + ". " + ScoringUtils.getPointsString(scoringOthers));
        }

        return scoringAgeUnder40 + scoringOthers;
    }
}
