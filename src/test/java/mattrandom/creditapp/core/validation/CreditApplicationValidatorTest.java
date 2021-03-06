package mattrandom.creditapp.core.validation;

import mattrandom.creditapp.core.Constants;
import mattrandom.creditapp.core.exception.ValidationException;
import mattrandom.creditapp.core.model.*;
import mattrandom.creditapp.core.validation.reflection.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static mattrandom.creditapp.core.Constants.*;
import static mattrandom.creditapp.core.model.CreditApplicationTestFactory.*;
import static mattrandom.creditapp.util.AgeUtils.generateBirthDate;

class CreditApplicationValidatorTest {
    private List<ClassAnnotationProcessor> classProcessors = List.of(new ExactlyOneNotNullAnnotationProcessor());
    private List<FieldAnnotationProcessor> fieldProcessors = List.of(new NotNullAnnotationProcessor(), new RegexAnnotationProcessor());
    private ObjectValidator objectValidator = new ObjectValidator(fieldProcessors, classProcessors);
    private CreditApplicationValidator cut = new CreditApplicationValidator(objectValidator);

    @Test
    public void test() throws ValidationException {
        // given
        final FamilyMember beatrice = new FamilyMember("Beatrice", generateBirthDate(18));
        final FamilyMember jane = new FamilyMember("Jane", generateBirthDate(40));
        final FamilyMember susie = new FamilyMember("Susie", generateBirthDate(5));
        List<FamilyMember> familyMembers = Arrays.asList(beatrice, jane, susie);

        NaturalPerson person = NaturalPerson.Builder
                .create()
                .withPesel("12341234123")
                .withFamilyMembers(familyMembers)
                .withPersonalData(PersonalData.Builder
                        .create()
                        .withName("Test")
                        .withLastName("Test")
                        .withMothersMaidenName("Test")
                        .withMaritalStatus(MaritalStatus.MARRIED)
                        .withEducation(Education.MIDDLE)
                        .build())
                .withContactData(ContactData.Builder
                        .create()
                        .withHomeAddress(new Address("Test", "Test", "66", "12-345", "Test"))
                        .withCorrespondenceAddress(new Address("Test", "Test", "66", "12-345", "Test"))
                        .withEmail("matt@random.com")
                        .withPhoneNumber("111222345")
                        .build())
                .withFinanceData(new FinanceData(new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, 10000.00)))
                .build();

        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 50000.00, 30);
        Set<Guarantor> guarantorSet = Set.of(new Guarantor("12312312399", generateBirthDate(18)), new Guarantor("12312312390", generateBirthDate(41)));
        CreditApplication creditApplication = new CreditApplication(DEFAULT_SYSTEM_LOCALE, CLIENT_TIME_ZONE, person, purposeOfLoan, guarantorSet);

        // when
        cut.validate(creditApplication);
    }
}