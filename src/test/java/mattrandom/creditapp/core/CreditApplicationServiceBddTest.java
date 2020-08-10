package mattrandom.creditapp.core;

import mattrandom.creditapp.core.exception.RequirementNotMetCause;
import mattrandom.creditapp.core.model.*;
import mattrandom.creditapp.core.scoring.EducationCalculator;
import mattrandom.creditapp.core.scoring.GuarantorsCalculator;
import mattrandom.creditapp.core.scoring.IncomeCalculator;
import mattrandom.creditapp.core.scoring.MaritalStatusCalculator;
import mattrandom.creditapp.core.validation.*;
import mattrandom.creditapp.core.validation.reflection.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreditApplicationServiceBddTest {
    private EducationCalculator educationCalculator = new EducationCalculator();
    private MaritalStatusCalculator maritalStatusCalculator = new MaritalStatusCalculator();
    private IncomeCalculator incomeCalculator = new IncomeCalculator();
    private SelfEmployedScoringCalculator selfEmployedScoringCalculator = new SelfEmployedScoringCalculator();
    private GuarantorsCalculator guarantorsCalculator = new GuarantorsCalculator();
    private PersonScoringCalculatorFactory personScoringCalculatorFactory = new PersonScoringCalculatorFactory(selfEmployedScoringCalculator, educationCalculator, maritalStatusCalculator, incomeCalculator, guarantorsCalculator);
    private Set<ClassAnnotationProcessor> classProcessors = Set.of(new ExactlyOneNotNullAnnotationProcessor());
    private Set<FieldAnnotationProcessor> fieldProcessors = Set.of(new NotNullAnnotationProcessor(), new RegexAnnotationProcessor());
    final ObjectValidator objectValidator = new ObjectValidator(fieldProcessors, classProcessors);
    private CreditApplicationValidator creditApplicationValidator = new CreditApplicationValidator(objectValidator);
    private CompoundPostValidator compoundPostValidator = new CompoundPostValidator(new PurposeOfLoanPostValidator(), new ExpensesPostValidator());
    private CreditApplicationService cut = new CreditApplicationService(personScoringCalculatorFactory, new CreditRatingCalculator(), creditApplicationValidator, compoundPostValidator);

    @Test
    @DisplayName("should return Decision is NEGATIVE_REQUIREMENTS_NOT_MET, min loan amount requirement is not met")
    public void test1() {
        //given
        List<FamilyMember> familyMemberList = Arrays.asList(new FamilyMember("John",18));
        NaturalPerson person = NaturalPerson.Builder
                .create()
                .withPesel("12312312312")
                .withContactData(ContactData.Builder
                        .create()
                        .withHomeAddress(new Address("Test", "Test", "66", "12-345", "Test"))
                        .withCorrespondenceAddress(new Address("Test", "Test", "66", "12-345", "Test"))
                        .withEmail("matt@random.com")
                        .withPhoneNumber("111222345")
                        .build())
                .withFamilyMembers(familyMemberList)
                .withPersonalData(PersonalData.Builder
                        .create()
                        .withName("Test")
                        .withLastName("Test")
                        .withMothersMaidenName("Test")
                        .withEducation(Education.MIDDLE)
                        .withMaritalStatus(MaritalStatus.MARRIED)
                        .build())
                .withFinanceData(new FinanceData(new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, 10000.00)))
                .build();

        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 50000.00, 30);
        CreditApplication creditApplication = CreditApplicationTestFactory.create(person, purposeOfLoan);

        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);

        //then
        assertEquals(DecisionType.NEGATIVE_REQUIREMENTS_NOT_MET, decision.getType());
        assertEquals(600, decision.getScoring());
        assertEquals(360000.00, decision.getCreditRate());
    }

    @Test
    @DisplayName("should return Decision is NEGATIVE when years since founded < 2")
    public void test2() {
        //given
        List<FamilyMember> familyMemberList = Arrays.asList(new FamilyMember("John",18));
        SelfEmployed person = SelfEmployed.Builder
                .create()
                .withNip("1234567")
                .withContactData(ContactData.Builder
                        .create()
                        .withHomeAddress(new Address("Test", "Test", "66", "12-345", "Test"))
                        .withCorrespondenceAddress(new Address("Test", "Test", "66", "12-345", "Test"))
                        .withEmail("matt@random.com")
                        .withPhoneNumber("111222345")
                        .build())
                .withFamilyMembers(familyMemberList)
                .withPersonalData(PersonalData.Builder
                        .create()
                        .withName("Test")
                        .withLastName("Test")
                        .withMothersMaidenName("Test")
                        .withEducation(Education.MIDDLE)
                        .withMaritalStatus(MaritalStatus.MARRIED)
                        .build())
                .withFinanceData(new FinanceData(new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, 7000.00)))
                .withYearsSinceFounded(1)
                .build();

        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 500000.00, 30);
        CreditApplication creditApplication = CreditApplicationTestFactory.create(person, purposeOfLoan);

        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);

        //then
        assertEquals(DecisionType.NEGATIVE_SCORING, decision.getType());
        assertEquals(200, decision.getScoring());
    }

    @Test
    @DisplayName("should return Decision is CONTACT_REQUIRED when years since founded >= 2")
    public void test3() {
        //given
        List<FamilyMember> familyMemberList = Arrays.asList(new FamilyMember("John",18));
        SelfEmployed person = SelfEmployed.Builder
                .create()
                .withNip("1234567")
                .withContactData(ContactData.Builder
                        .create()
                        .withHomeAddress(new Address("Test", "Test", "66", "12-345", "Test"))
                        .withCorrespondenceAddress(new Address("Test", "Test", "66", "12-345", "Test"))
                        .withEmail("matt@random.com")
                        .withPhoneNumber("111222345")
                        .build())
                .withFamilyMembers(familyMemberList)
                .withPersonalData(PersonalData.Builder
                        .create()
                        .withName("Test")
                        .withLastName("Test")
                        .withMothersMaidenName("Test")
                        .withEducation(Education.MIDDLE)
                        .withMaritalStatus(MaritalStatus.MARRIED)
                        .build())
                .withFinanceData(new FinanceData(new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, 7000.00)))
                .withYearsSinceFounded(3)
                .build();

        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 500000.00, 30);
        CreditApplication creditApplication = CreditApplicationTestFactory.create(person, purposeOfLoan);

        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);

        //then
        assertEquals(DecisionType.CONTACT_REQUIRED, decision.getType());
        assertEquals(400, decision.getScoring());
    }

    @Test
    @DisplayName("should return Decision is negative requirements not met, cause too high personal expenses")
    public void test4() {
        //given
        Set<Expense> expenseSet = Set.of(new Expense("1", ExpenseType.PERSONAL, 500.00), new Expense("2", ExpenseType.PERSONAL, 750.00));
        final FinanceData financeData = new FinanceData(expenseSet, new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, 2000.00));
        SelfEmployed person = SelfEmployed.Builder
                .create()
                .withNip("1234567")
                .withContactData(ContactData.Builder
                        .create()
                        .withHomeAddress(new Address("Test", "Test", "66", "12-345", "Test"))
                        .withCorrespondenceAddress(new Address("Test", "Test", "66", "12-345", "Test"))
                        .withEmail("matt@random.com")
                        .withPhoneNumber("111222345")
                        .build())
                .withPersonalData(PersonalData.Builder
                        .create()
                        .withName("Test")
                        .withLastName("Test")
                        .withMothersMaidenName("Test")
                        .withEducation(Education.MIDDLE)
                        .withMaritalStatus(MaritalStatus.MARRIED)
                        .build())
                .withFinanceData(financeData)
                .withYearsSinceFounded(3)
                .build();

        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 500000.00, 30);
        CreditApplication creditApplication = CreditApplicationTestFactory.create(person, purposeOfLoan);

        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);

        //then
        assertEquals(DecisionType.NEGATIVE_REQUIREMENTS_NOT_MET, decision.getType());
        assertTrue(decision.getRequirementNotMetCause().isPresent());
        assertEquals(RequirementNotMetCause.TOO_HIGH_PERSONAL_EXPENSES, decision.getRequirementNotMetCause().get());
    }
}
