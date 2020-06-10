package mattrandom.creditapp.core.model;

public class PersonTestFactory {

    public static Person create(double totalMonthlyIncomeInPln, int numOfDependants, Education education, MaritalStatus maritalStatus) {

        PersonalData personalData = new PersonalData("Test", "Test", "Test", maritalStatus,
                education, totalMonthlyIncomeInPln, numOfDependants);

        return new Person(personalData, null);
    }

}
