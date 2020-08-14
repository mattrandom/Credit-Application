package mattrandom.creditapp.client;

import mattrandom.creditapp.core.model.*;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static mattrandom.creditapp.util.AgeUtils.generateBirthDate;

public class DummyCreditApplicationReader implements CreditApplicationReader {

    @Override
    public CreditApplication read() {
        final FamilyMember beatrice = new FamilyMember("Beatrice", generateBirthDate(18));
        final FamilyMember jane = new FamilyMember("Jane", generateBirthDate(40));
        final FamilyMember susie = new FamilyMember("Susie", generateBirthDate(5));
        List<FamilyMember> familyMembers = Arrays.asList(beatrice, jane, susie);

        NaturalPerson person = NaturalPerson.Builder
                .create()
                .withContactData(ContactData.Builder.create()
                        .withEmail("test@test")
                        .withPhoneNumber("456456456")
                        .withHomeAddress(new Address("Testable", "Test", "66", "66-666", "Test"))
                        .withCorrespondenceAddress(new Address("Testable", "Test", "66", "66-666", "Test"))
                        .build())
                .withPesel("11122233345")
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
        Set<Guarantor> guarantorSet = Set.of(new Guarantor("12312312399", generateBirthDate(18)), new Guarantor("12312312390", generateBirthDate(41)));
        CreditApplication creditApplication = new CreditApplication(ZoneId.of("Europe/Warsaw"), person, purposeOfLoan, guarantorSet);

        return creditApplication;
    }
}
