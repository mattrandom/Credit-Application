package mattrandom.creditapp.core;

import mattrandom.creditapp.core.model.*;
import mattrandom.creditapp.core.scoring.EducationCalculator;
import mattrandom.creditapp.core.scoring.IncomeCalculator;
import mattrandom.creditapp.core.scoring.MaritalStatusCalculator;
import mattrandom.creditapp.core.validation.CreditApplicationValidator;
import mattrandom.creditapp.core.validation.PersonValidator;
import mattrandom.creditapp.core.validation.PersonalDataValidator;
import mattrandom.creditapp.core.validation.PurposeOfLoanValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditApplicationServiceBddTest {
    private EducationCalculator educationCalculator = new EducationCalculator();
    private MaritalStatusCalculator maritalStatusCalculator = new MaritalStatusCalculator();
    private IncomeCalculator incomeCalculator = new IncomeCalculator();
    private SelfEmployedScoringCalculator selfEmployedScoringCalculator = new SelfEmployedScoringCalculator();
    private PersonScoringCalculatorFactory personScoringCalculatorFactory = new PersonScoringCalculatorFactory(selfEmployedScoringCalculator, educationCalculator, maritalStatusCalculator, incomeCalculator);
    private CreditApplicationValidator creditApplicationValidator = new CreditApplicationValidator(new PersonValidator(new PersonalDataValidator()), new PurposeOfLoanValidator());
    private CreaditApplicationService cut = new CreaditApplicationService(personScoringCalculatorFactory, new CreditRatingCalculator(), creditApplicationValidator);

    @Test
    @DisplayName("should return Decision is NEGATIVE_REQUIREMENTS_NOT_MET, min loan amount requirement is not met")
    public void test1() {
        //given
        List<FamilyMember> familyMemberList = Arrays.asList(new FamilyMember("John",18));
        NaturalPerson person = NaturalPerson.Builder
                .create()
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
}
