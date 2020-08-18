package mattrandom.creditapp.core.scoring;

import mattrandom.creditapp.core.bik.BikApi;
import mattrandom.creditapp.core.bik.ScoringRequest;
import mattrandom.creditapp.core.bik.ScoringResponse;
import mattrandom.creditapp.core.model.NaturalPerson;
import mattrandom.creditapp.core.model.Person;
import mattrandom.creditapp.core.model.SelfEmployed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BikScoringCalculator implements ScoringCalculator {
    private static final Logger log = LoggerFactory.getLogger(BikScoringCalculator.class);

    private final BikApi bikApi;

    public BikScoringCalculator(BikApi bikApi) {
        this.bikApi = bikApi;
    }

    @Override
    public int calculate(Person person) {
        ScoringRequest request = new ScoringRequest();

        if (person instanceof SelfEmployed) {
            request.setNip(((SelfEmployed) person).getNip());
        }
        if (person instanceof NaturalPerson) {
            request.setPesel(((NaturalPerson) person).getPesel());
        }

        final ScoringResponse response = bikApi.getScoring(request);
        int scoring = response.getScoring() / 6;

        log.info("Bik scoring={}/600, scoring={}/100. {}", response.getScoring(), scoring, ScoringUtils.getPointsString(scoring));

        return scoring;
    }
}
