package mattrandom.creditapp.core.scoring;

import mattrandom.creditapp.core.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Testing the return of additional points depending on the age of the guarantor")
public class GuarantorCalculatorTest {
    private GuarantorsCalculator cut = new GuarantorsCalculator();

    @Test
    @DisplayName("should return 75 points when one of the guarantors is under 40 years and the other is over 40 years")
    public void test1() {
        //given
        Set<Guarantor> guarantorSet = Set.of(new Guarantor("45645645645", 18), new Guarantor("45645645646", 41));
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 50000.00, 30);
        NaturalPerson person = createNaturalPerson();
        CreditApplication creditApplication = CreditApplicationTestFactory.create(person, purposeOfLoan, guarantorSet);
        //when
        int scoring = cut.calculate(creditApplication);
        //then
        assertEquals(75, scoring);
    }

    @Test
    @DisplayName("should return 50 points when guarantor is under 40 years")
    public void test2() {
        //given
        Set<Guarantor> guarantorSet = Set.of(new Guarantor("45645645645", 18));
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 50000.00, 30);
        NaturalPerson person = createNaturalPerson();
        CreditApplication creditApplication = CreditApplicationTestFactory.create(person, purposeOfLoan, guarantorSet);
        //when
        int scoring = cut.calculate(creditApplication);
        //then
        assertEquals(50, scoring);
    }

    private NaturalPerson createNaturalPerson() {
        return NaturalPerson.Builder
                    .create()
                    .withFamilyMembers(new ArrayList<>())
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
    }
}
