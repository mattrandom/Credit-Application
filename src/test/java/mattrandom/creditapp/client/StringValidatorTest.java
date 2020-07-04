package mattrandom.creditapp.client;

import mattrandom.creditapp.core.Constants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringValidatorTest {

    @Test
    @DisplayName("should return true when name has 3-10 characters and first letter is upper case")
    public void test1() {
        //given
        String input = "Mateo";
        //when
        boolean isTrue = StringValidator.validateString(input, Constants.NAME_REGEX);
        //then
        assertTrue(isTrue);
    }

    @Test
    @DisplayName("should return false when name has 3-10 characters and first letter is not upper case")
    public void test2() {
        //given
        String input = "mateo";
        //when
        boolean isTrue = StringValidator.validateString(input, Constants.NAME_REGEX);
        //then
        assertFalse(isTrue);
    }

    @Test
    @DisplayName("should return false when name has less than 3 characters")
    public void test3() {
        //given
        String input = "Ma";
        //when
        boolean isTrue = StringValidator.validateString(input, Constants.NAME_REGEX);
        //then
        assertFalse(isTrue);
    }

    @Test
    @DisplayName("should return false when name has more than 10 characters")
    public void test4() {
        //given
        String input = "Mateorandom";
        //when
        boolean isTrue = StringValidator.validateString(input, Constants.NAME_REGEX);
        //then
        assertFalse(isTrue);
    }

    @Test
    @DisplayName("should return false when last name has more than two-part")
    public void test5() {
        //given
        String input = "Sicmunduc Creatus Est";
        //when
        boolean isTrue = StringValidator.validateString(input, Constants.LAST_NAME_REGEX);
        //then
        assertFalse(isTrue);
    }

    @Test
    @DisplayName("should return false when first letter of last name is not upper case")
    public void test6() {
        //given
        String input = "sicmundus";
        //when
        boolean isTrue = StringValidator.validateString(input, Constants.LAST_NAME_REGEX);
        //then
        assertFalse(isTrue);
    }

    @Test
    @DisplayName("should return true when last name terms are separated with space")
    public void test7() {
        //given
        String input = "Sicmunduc Creatus";
        //when
        boolean isTrue = StringValidator.validateString(input, Constants.LAST_NAME_REGEX);
        //then
        assertTrue(isTrue);
    }

    @Test
    @DisplayName("should return true when last name terms are separated with dash")
    public void test8() {
        //given
        String input = "Sicmunduc-Creatus";
        //when
        boolean isTrue = StringValidator.validateString(input, Constants.LAST_NAME_REGEX);
        //then
        assertTrue(isTrue);
    }

    @Test
    @DisplayName("should return false when e-mail adress has not @ in signature")
    public void test9() {
        //given
        String input = "sicmundus$creatus.est";
        //when
        boolean isTrue = StringValidator.validateString(input, Constants.EMAIL_REGEX);
        //then
        assertFalse(isTrue);
    }

    @Test
    @DisplayName("should return true when e-mail adress has following signature: xxx@xxx.xx")
    public void test10() {
        //given
        String input = "xxx@xxx.xx";
        //when
        boolean isTrue = StringValidator.validateString(input, Constants.EMAIL_REGEX);
        //then
        assertTrue(isTrue);
    }


}
