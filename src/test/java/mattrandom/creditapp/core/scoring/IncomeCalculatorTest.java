package mattrandom.creditapp.core.scoring;

import mattrandom.creditapp.core.model.Person;
import mattrandom.creditapp.core.model.PersonTestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testing income per person depending on total monthly income and number of dependants")
public class IncomeCalculatorTest {

    private IncomeCalculator cut = new IncomeCalculator();

    @Test
    @DisplayName("should return 200 when income = 5000 and numOfDependants = 2")
    public void test() {
        //given
        Person person = PersonTestFactory.create(5000, 2);
        //when
        int scoring = cut.calculate(person);
        //then
        assertEquals(200, scoring);
    }
}
