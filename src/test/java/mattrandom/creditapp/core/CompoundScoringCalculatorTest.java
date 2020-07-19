package mattrandom.creditapp.core;

import mattrandom.creditapp.core.model.CreditApplication;
import mattrandom.creditapp.core.model.CreditApplicationTestFactory;
import mattrandom.creditapp.core.model.Person;
import mattrandom.creditapp.core.model.PersonTestFactory;
import mattrandom.creditapp.core.scoring.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;

class CompoundScoringCalculatorTest {
    private ScoringCalculator calculator1Mock = Mockito.mock(ScoringCalculator.class);
    private ScoringCalculator calculator2Mock = Mockito.mock(ScoringCalculator.class);
    private ScoringCalculator calculator3Mock = Mockito.mock(ScoringCalculator.class);

    private CompoundScoringCalculator cut = new CompoundScoringCalculator(calculator1Mock, calculator2Mock, calculator3Mock);

    @Test
    @DisplayName("should return sum of calculations")
    public void test1() {
        //given
        CreditApplication creditApplication = CreditApplicationTestFactory.create();
        BDDMockito.given(calculator1Mock.calculate(eq(creditApplication))).willReturn(50);
        BDDMockito.given(calculator2Mock.calculate(eq(creditApplication))).willReturn(200);
        BDDMockito.given(calculator3Mock.calculate(eq(creditApplication))).willReturn(100);
        //when
        int scoring = cut.calculate(creditApplication);
        //then
        assertEquals(350, scoring);
    }
}