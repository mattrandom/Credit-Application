package mattrandom.creditapp.core.model;

public class PersonTestFactory {

    public static Person create(double income, int numOfDependants) {
        PersonalData personalData = new PersonalData("test", "test", "test", MaritalStatus.SINGLE, Education.MIDDLE, income, numOfDependants);
        return new Person(personalData, null);
    }

    public static Person create(MaritalStatus maritalStatus) {
        PersonalData personalData = new PersonalData("test", "test", "test", maritalStatus, Education.MIDDLE, 5000, 2);
        return new Person(personalData, null);
    }

    public static Person create(Education education) {
        PersonalData personalData = new PersonalData("test", "test", "test", MaritalStatus.SINGLE, education, 5000, 2);
        return new Person(personalData, null);
    }

    public static Person create() {
        PersonalData personalData = new PersonalData("test", "test", "test", MaritalStatus.SINGLE, Education.MIDDLE, 5000,2);
        return new Person(personalData, null);
    }

    public static Person create(double totalMonthlyIncomeInPln, int numOfDependants, Education education, MaritalStatus maritalStatus) {
        PersonalData personalData = new PersonalData("Test", "Test", "Test", maritalStatus,
                education, totalMonthlyIncomeInPln, numOfDependants);
        return new Person(personalData, null);
    }
}