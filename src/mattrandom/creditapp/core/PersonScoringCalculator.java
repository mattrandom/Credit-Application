package mattrandom.creditapp.core;

import mattrandom.creditapp.core.model.Person;

public class PersonScoringCalculator {

    public int calculate(Person person) {
        double incomePerFamilyMember = person.getPersonalData().getTotalMonthlyIncomeInPln() / person.getPersonalData().getNumOfFamilyDependants();
        int pointsForIncome = (int) (incomePerFamilyMember / 1000) * 100;

        int maritalStatusScoring =  person.getPersonalData().getMaritalStatus().getScoringPoints();
        int educationLevelScoring = person.getPersonalData().getEducation().getScoringPoints();

        return pointsForIncome + maritalStatusScoring + educationLevelScoring;

    }
}
