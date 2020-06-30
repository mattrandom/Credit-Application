package mattrandom.creditapp.core.model;

public class FinanceData {
    private final SourceOfIncome[] sourcesOfIncome;

    public FinanceData(SourceOfIncome... sourceOfIncome) {
        this.sourcesOfIncome = sourceOfIncome;
    }

    public SourceOfIncome[] getSourcesOfIncome() {
        return sourcesOfIncome;
    }
}
