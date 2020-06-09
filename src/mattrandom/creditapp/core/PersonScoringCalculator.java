package mattrandom.creditapp.core;

public class PersonScoringCalculator {

    public int calculate(Person person) {
        double incomePerFamilyMember = person.getTotalMonthlyIncomeInPln() / person.getNumOfFamilyDependants();
        int pointsForIncome = (int) (incomePerFamilyMember / 1000) * 100;
        int pointsForMarried = person.isMarried() ? 100 : 0;
        return pointsForIncome + pointsForMarried;

    }
}
