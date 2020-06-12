package mattrandom.creditapp.core.scoring;

import mattrandom.creditapp.core.model.MaritalStatus;
import mattrandom.creditapp.core.model.Person;
import mattrandom.creditapp.core.model.PersonTestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class MaritalStatusCalculatorTest {

    private MaritalStatusCalculator cut = new MaritalStatusCalculator();

    @ParameterizedTest
    @DisplayName("should return points attached to enum element")
    @EnumSource(MaritalStatus.class)
    public void test(MaritalStatus maritalStatus) {
        //given
        Person person = PersonTestFactory.create(maritalStatus);
        //when
        int scoring = cut.calculate(person);
        //then
        assertEquals(maritalStatus.getScoringPoints(), scoring);
    }
}
