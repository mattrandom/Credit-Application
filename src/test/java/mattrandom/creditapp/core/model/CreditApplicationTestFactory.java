package mattrandom.creditapp.core.model;

public class CreditApplicationTestFactory {

    public static CreditApplication create() {
        Person person = PersonTestFactory.create(5000.00, 2, Education.MIDDLE,MaritalStatus.SEPARATED);
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 100000, 35);
        CreditApplication creditApplication = new CreditApplication(person, purposeOfLoan);
        return creditApplication;
    }

    public static CreditApplication create(PurposeOfLoanType purposeOfLoanType, double expectedLoanAmount, int expectedLoanPeriod, double totalMonthlyIncomeInPln, int numOfDependants){
        Person person = PersonTestFactory.create(totalMonthlyIncomeInPln, numOfDependants, Education.MIDDLE,MaritalStatus.SEPARATED);

        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(purposeOfLoanType,expectedLoanAmount,expectedLoanPeriod);
        CreditApplication creditApplication = new CreditApplication(person,purposeOfLoan);
        return creditApplication;
    }
}
