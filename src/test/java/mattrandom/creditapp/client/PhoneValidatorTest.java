package mattrandom.creditapp.client;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneValidatorTest {

    @Test
    @DisplayName("should return true when number is nine-digits")
    public void test1() {
        //given
        String input = "999999999";
        //when
        boolean isTrue = PhoneValidator.validate(input);
        //then
        assertTrue(isTrue);
    }

    @Test
    @DisplayName("should return true when number is twelve-digits including country code")
    public void test2() {
        //given
        String input = "+48999999999";
        //when
        boolean isTrue = PhoneValidator.validate(input);
        //then
        assertTrue(isTrue);
    }

    @Test
    @DisplayName("should be false when number is twelve-digits including country code without + sign")
    public void test3() {
        //given
        String input = "048999999999";
        //when
        boolean isTrue = PhoneValidator.validate(input);
        //then
        assertFalse(isTrue);
    }

    @Test
    @DisplayName("should be false when number is longer than twelve-digits")
    public void test4() {
        //given
        String input = "+489999999991";
        //when
        boolean isTrue = PhoneValidator.validate(input);
        //then
        assertFalse(isTrue);
    }

    @Test
    @DisplayName("should be false when number is shorter than nine-digits")
    public void test5() {
        //given
        String input = "99999999";
        //when
        boolean isTrue = PhoneValidator.validate(input);
        //then
        assertFalse(isTrue);
    }
}
