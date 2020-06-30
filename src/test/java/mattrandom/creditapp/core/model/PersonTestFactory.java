package mattrandom.creditapp.core.model;

public class PersonTestFactory {

    public static Person create(int numOfDependants, SourceOfIncome... sourcesOfIncomes) {
        PersonalData personalData = new PersonalData("test", "test", "test", MaritalStatus.SINGLE, Education.MIDDLE, numOfDependants);
        return new Person(personalData, null, new FinanceData(sourcesOfIncomes));
    }

    public static Person create(MaritalStatus maritalStatus) {
        PersonalData personalData = new PersonalData("test", "test", "test", maritalStatus, Education.MIDDLE,2);
        return new Person(personalData, null, null);
    }

    public static Person create(Education education) {
        PersonalData personalData = new PersonalData("test", "test", "test", MaritalStatus.SINGLE, education,2);
        return new Person(personalData, null, null);
    }

    public static Person create() {
        PersonalData personalData = new PersonalData("test", "test", "test", MaritalStatus.SINGLE, Education.MIDDLE,2);
        return new Person(personalData, null, null);
    }

    public static Person create(double totalMonthlyIncomeInPln, int numOfDependants, Education education, MaritalStatus maritalStatus) {
        PersonalData personalData = new PersonalData("Test", "Test", "Test", maritalStatus,
                education, numOfDependants);
        return new Person(personalData, null, new FinanceData(new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, totalMonthlyIncomeInPln)));
    }
}