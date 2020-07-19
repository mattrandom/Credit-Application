package mattrandom.creditapp.core;

import mattrandom.creditapp.core.exception.ValidationException;
import mattrandom.creditapp.core.model.CreditApplication;
import mattrandom.creditapp.core.model.CreditApplicationTestFactory;
import mattrandom.creditapp.core.model.Person;
import mattrandom.creditapp.core.scoring.ScoringCalculator;
import mattrandom.creditapp.core.validation.CreditApplicationValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class CreditApplicationServiceTest {

    @InjectMocks
    private CreditApplicationService cut;

    @Mock
    private ScoringCalculator scoringCalculatorMock;
    @Mock
    private PersonScoringCalculatorFactory personScoringCalculatorFactoryMock;
    @Mock
    private CreditRatingCalculator creditRatingCalculatorMock;
    @Mock
    private CreditApplicationValidator creditApplicationValidatorMock;

    @BeforeEach
    public void init() throws ValidationException {
        BDDMockito.given(personScoringCalculatorFactoryMock.getCalculator(any(Person.class)))
                .willReturn(scoringCalculatorMock);

        BDDMockito.doNothing()
                .when(creditApplicationValidatorMock)
                .validate(any(CreditApplication.class));
    }

    @Test
    @DisplayName("Should return NEGATIVE_SCORING decision when scoring is < 300")
    public void test1() {
        //given
        CreditApplication creditApplication = CreditApplicationTestFactory.create();
        BDDMockito.given(scoringCalculatorMock.calculate(eq(creditApplication))).willReturn(100);
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
        BDDMockito.given(scoringCalculatorMock.calculate(eq(creditApplication))).willReturn(350);
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
        BDDMockito.given(scoringCalculatorMock.calculate(eq(creditApplication))).willReturn(500);
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
        CreditApplication creditApplication = CreditApplicationTestFactory.create(150000.00);
        BDDMockito.given(scoringCalculatorMock.calculate(eq(creditApplication))).willReturn(450);
        BDDMockito.given(creditRatingCalculatorMock.calculate(eq(creditApplication))).willReturn(151000.00);
        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);
        //then
        assertEquals(DecisionType.POSITIVE, decision.getType());
    }
}
