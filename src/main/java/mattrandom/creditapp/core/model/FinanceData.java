package mattrandom.creditapp.core.model;

import java.util.Arrays;
import java.util.List;

public class FinanceData {
    private final List<SourceOfIncome> sourcesOfIncome;

    public FinanceData(SourceOfIncome... sourceOfIncome) {
        this.sourcesOfIncome = Arrays.asList(sourceOfIncome);
    }

    public List<SourceOfIncome> getSourcesOfIncome() {
        return sourcesOfIncome;
    }
}
