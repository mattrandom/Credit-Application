package mattrandom.creditapp.client;

import mattrandom.creditapp.core.model.*;

import java.util.Arrays;
import java.util.List;

public class DummyCreditApplicationReader implements CreditApplicationReader {

    @Override
    public CreditApplication read() {
        final FamilyMember beatrice = new FamilyMember("Beatrice", 18);
        final FamilyMember jane = new FamilyMember("Jane", 40);
        final FamilyMember susie = new FamilyMember("Susie", 5);
        List<FamilyMember> familyMembers = Arrays.asList(beatrice, jane, susie);

        NaturalPerson person = NaturalPerson.Builder
                .create()
                .withFamilyMembers(familyMembers)
                .withPersonalData(PersonalData.Builder
                        .create()
                        .withName("Test")
                        .withLastName("Test")
                        .withMothersMaidenName("Test")
                        .withMaritalStatus(MaritalStatus.MARRIED)
                        .withEducation(Education.MIDDLE)
                        .build())
                .withFinanceData(new FinanceData(new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, 10000.00)))
                .build();

        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 50000.00, 30);
        CreditApplication creditApplication = new CreditApplication(person, purposeOfLoan);

        return creditApplication;
    }
}
