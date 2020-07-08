package mattrandom.creditapp.client;

import static mattrandom.creditapp.core.Constants.PHONE_REGEX;

public class PhoneValidator {
    public static boolean validate(String input) {
        return input.matches(PHONE_REGEX);
    }
}
