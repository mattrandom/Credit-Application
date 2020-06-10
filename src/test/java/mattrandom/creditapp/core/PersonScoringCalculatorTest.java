package mattrandom.creditapp.core;

import mattrandom.creditapp.core.model.Education;
import mattrandom.creditapp.core.model.MaritalStatus;
import mattrandom.creditapp.core.model.Person;
import mattrandom.creditapp.core.model.PersonTestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testing scoring depending on income, education and marital status")
class PersonScoringCalculatorTest {

    private PersonScoringCalculator cut = new PersonScoringCalculator();

    @Test
    @DisplayName("should return 200 when income = 5000, numOfDependants = 2, education = PRIMARY and maritalStatus = MARRIED")
    public void shouldReturn200WhenIncomeFor2Is5000EducationIsPrimaryAndStatusIsMarried() {
        //given
        Person person = PersonTestFactory.create(5000, 2, Education.PRIMARY, MaritalStatus.MARRIED);
        //when
        int scoring = cut.calculate(person);
        //then
        assertEquals(200, scoring);
    }

    @Test
    @DisplayName("should return 500 when income = 5500, numOfDependants = 1, education = MIDDLE and maritalStatus = DIVORCED")
    public void shouldReturn500WhenIncomeFor1Is5500EducationIsMiddleAndStatusIsDivorced() {
        //given
        Person person = PersonTestFactory.create(5500, 1, Education.MIDDLE, MaritalStatus.DIVORCED);
        //when
        int scoring = cut.calculate(person);
        //then
        assertEquals(500, scoring);
    }

    @Test
    @DisplayName("should return 100 when income = 9000, numOfDependants = 3, education = NONE and maritalStatus = SINGLE")
    public void shouldReturn100WhenIncomeFor1Is9000EducationIsNoneAndStatusIsSingle() {
        //given
        Person person = PersonTestFactory.create(9000, 3, Education.NONE, MaritalStatus.SINGLE);
        //when
        int scoring = cut.calculate(person);
        //then
        assertEquals(100, scoring);
    }
}