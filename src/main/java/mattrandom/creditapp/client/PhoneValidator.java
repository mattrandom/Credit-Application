package mattrandom.creditapp.client;

import mattrandom.creditapp.core.Constants;

public class PhoneValidator {
    public static boolean validate(String input) {
        return input.matches(Constants.PHONE_REGEX);
    }
}
