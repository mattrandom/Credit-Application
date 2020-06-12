package mattrandom.creditapp.core;

import mattrandom.creditapp.core.model.CreditApplication;
import mattrandom.creditapp.core.model.CreditApplicationTestFactory;
import mattrandom.creditapp.core.model.PurposeOfLoanType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class CreditApplicationServiceTest {

    @InjectMocks
    private CreaditApplicationService cut;

    @Mock
    private PersonScoringCalculator calculatorMock;

    @Mock
    private CreditRatingCalculator creditRatingCalculatorMock;

    @Test
    @DisplayName("Should return NEGATIVE_SCORING decision when scoring is < 300")
    public void test1() {
        //given
        CreditApplication creditApplication = CreditApplicationTestFactory.create();
        BDDMockito.given(calculatorMock.calculate(creditApplication.getPerson())).willReturn(100);
        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);
        //then
        assertEquals(DecisionType.NEGATIVE_SCORING, decision.getType());
    }

    @Test
    @DisplayName("Should return CONTACT_REQUIRED decision when scoring is <= 400")
    public void test2() {
        //given
        CreditApplication creditApplication = CreditApplicationTestFactory.create();
        BDDMockito.given(calculatorMock.calculate(creditApplication.getPerson())).willReturn(350);
        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);
        //then
        assertEquals(DecisionType.CONTACT_REQUIRED, decision.getType());
    }

    @Test
    @DisplayName("Should return NEGATIVE_RATING decision when scoring is > 400 and credit rating < expected loan amount")
    public void test3() {
        //given
        CreditApplication creditApplication = CreditApplicationTestFactory.create(190000.00);
        BDDMockito.given(calculatorMock.calculate(creditApplication.getPerson())).willReturn(500);
        BDDMockito.given(creditRatingCalculatorMock.calculate(eq(creditApplication))).willReturn(189000.00);
        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);
        //then
        assertEquals(DecisionType.NEGATIVE_RATING, decision.getType());
    }

    @Test
    @DisplayName("Should return POSITIVE decision when scoring is > 400 and credit rating >= expected loan amount")
    public void test4() {
        //given
        CreditApplication creditApplication = CreditApplicationTestFactory.create(50000.00);
        BDDMockito.given(calculatorMock.calculate(creditApplication.getPerson())).willReturn(500);
        BDDMockito.given(creditRatingCalculatorMock.calculate(eq(creditApplication))).willReturn(51000.00);
        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);
        //then
        assertEquals(DecisionType.POSITIVE, decision.getType());
    }
}
