package mattrandom.creditapp.core;

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
    private PersonCalculator calculator1Mock = Mockito.mock(PersonCalculator.class);
    private PersonCalculator calculator2Mock = Mockito.mock(PersonCalculator.class);
    private PersonCalculator calculator3Mock = Mockito.mock(PersonCalculator.class);

    private CompoundScoringCalculator cut = new CompoundScoringCalculator(calculator1Mock, calculator2Mock, calculator3Mock);

    @Test
    @DisplayName("should return sum of calculations")
    public void test1() {
        //given
        Person person = PersonTestFactory.create();
        BDDMockito.given(calculator1Mock.calculate(eq(person))).willReturn(50);
        BDDMockito.given(calculator2Mock.calculate(eq(person))).willReturn(200);
        BDDMockito.given(calculator3Mock.calculate(eq(person))).willReturn(100);
        //when
        int scoring = cut.calculate(person);
        //then
        assertEquals(350, scoring);
    }
}