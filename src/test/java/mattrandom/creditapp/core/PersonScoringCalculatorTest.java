package mattrandom.creditapp.core;

import mattrandom.creditapp.core.model.Person;
import mattrandom.creditapp.core.model.PersonTestFactory;
import mattrandom.creditapp.core.scoring.EducationCalculator;
import mattrandom.creditapp.core.scoring.IncomeCalculator;
import mattrandom.creditapp.core.scoring.MaritalStatusCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;

@DisplayName("Testing scoring depending on income, education and marital status")
@ExtendWith(MockitoExtension.class)
class PersonScoringCalculatorTest {

    @InjectMocks
    private PersonScoringCalculator cut;

    @Mock
    private IncomeCalculator incomeCalculatorMock;
    @Mock
    private MaritalStatusCalculator maritalCalculatorMock;
    @Mock
    private EducationCalculator educationCalculatorMock;

    @Test
    @DisplayName("should return sum of calculation")
    public void test1() {
        //given
        Person person = PersonTestFactory.create();
        BDDMockito.given(incomeCalculatorMock.calculate(eq(person))).willReturn(50);
        BDDMockito.given(maritalCalculatorMock.calculate(eq(person))).willReturn(200);
        BDDMockito.given(educationCalculatorMock.calculate(eq(person))).willReturn(100);
        //when
        int scoring = cut.calculate(person);
        //then
        assertEquals(350, scoring);
    }
}