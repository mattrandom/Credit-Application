package mattrandom.creditapp.core.model;

public class PersonTestFactory {

    public static Person create(int numOfDependants, SourceOfIncome... sourcesOfIncomes) {
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withMaritalStatus(MaritalStatus.SINGLE)
                .withEducation(Education.MIDDLE)
                .withNumOfFamilyDependants(numOfDependants)
                .build();
        return Person.Builder
                .create()
                .withPersonalData(personalData)
                .withFinanceData(new FinanceData(sourcesOfIncomes))
                .build();
    }

    public static Person create(MaritalStatus maritalStatus) {
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withMaritalStatus(maritalStatus)
                .withEducation(Education.MIDDLE)
                .withNumOfFamilyDependants(2)
                .build();
        return Person.Builder
                .create()
                .withPersonalData(personalData)
                .build();
    }

    public static Person create(Education education) {
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withMaritalStatus(MaritalStatus.SINGLE)
                .withEducation(education)
                .withNumOfFamilyDependants(2)
                .build();
        return Person.Builder
                .create()
                .withPersonalData(personalData)
                .build();
    }

    public static Person create() {
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withMaritalStatus(MaritalStatus.SINGLE)
                .withEducation(Education.MIDDLE)
                .withNumOfFamilyDependants(2)
                .build();
        return Person.Builder
                .create()
                .withPersonalData(personalData)
                .build();
    }

    public static Person create(double totalMonthlyIncomeInPln, int numOfDependants, Education education, MaritalStatus maritalStatus) {
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withMaritalStatus(maritalStatus)
                .withEducation(education)
                .withNumOfFamilyDependants(numOfDependants)
                .build();
        return Person.Builder
                .create()
                .withPersonalData(personalData)
                .withFinanceData(new FinanceData(new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, totalMonthlyIncomeInPln)))
                .build();
    }
}