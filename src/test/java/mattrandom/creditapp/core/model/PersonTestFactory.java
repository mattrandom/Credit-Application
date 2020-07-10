package mattrandom.creditapp.core.model;

public class PersonTestFactory {

    public static NaturalPerson create(int numOfDependants, SourceOfIncome... sourcesOfIncomes) {
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withMaritalStatus(MaritalStatus.SINGLE)
                .withEducation(Education.MIDDLE)
                .withNumOfFamilyDependants(numOfDependants)
                .build();
        return NaturalPerson.Builder
                .create()
                .withPersonalData(personalData)
                .withFinanceData(new FinanceData(sourcesOfIncomes))
                .build();
    }

    public static NaturalPerson create(MaritalStatus maritalStatus) {
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withMaritalStatus(maritalStatus)
                .withEducation(Education.MIDDLE)
                .withNumOfFamilyDependants(2)
                .build();
        return NaturalPerson.Builder
                .create()
                .withPersonalData(personalData)
                .build();
    }

    public static NaturalPerson create(Education education) {
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withMaritalStatus(MaritalStatus.SINGLE)
                .withEducation(education)
                .withNumOfFamilyDependants(2)
                .build();
        return NaturalPerson.Builder
                .create()
                .withPersonalData(personalData)
                .build();
    }

    public static NaturalPerson create() {
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withMaritalStatus(MaritalStatus.SINGLE)
                .withEducation(Education.MIDDLE)
                .withNumOfFamilyDependants(2)
                .build();
        return NaturalPerson.Builder
                .create()
                .withPersonalData(personalData)
                .build();
    }

    public static NaturalPerson create(double totalMonthlyIncomeInPln, int numOfDependants, Education education, MaritalStatus maritalStatus) {
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withMaritalStatus(maritalStatus)
                .withEducation(education)
                .withNumOfFamilyDependants(numOfDependants)
                .build();
        return NaturalPerson.Builder
                .create()
                .withPersonalData(personalData)
                .withFinanceData(new FinanceData(new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, totalMonthlyIncomeInPln)))
                .build();
    }
}