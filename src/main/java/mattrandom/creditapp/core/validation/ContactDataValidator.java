package mattrandom.creditapp.core.validation;

import mattrandom.creditapp.core.exception.ValidationException;
import mattrandom.creditapp.core.model.ContactData;
import mattrandom.creditapp.core.model.CreditApplication;

import static mattrandom.creditapp.core.Constants.*;

public class ContactDataValidator implements Validator {

    @Override
    public void validate(CreditApplication creditApplication) throws ValidationException {
        ContactData contactData = creditApplication.getPerson().getContactData();

        ValidationUtils.validateNotNull("e-mail", contactData.getEmail());
        ValidationUtils.validateRegex("e-mail", contactData.getEmail(), EMAIL_REGEX);

        ValidationUtils.validateNotNull("phone number", contactData.getPhoneNumber());
        ValidationUtils.validateRegex("phone number", contactData.getPhoneNumber(), PHONE_REGEX);
        ValidationUtils.validateMinValue("phone number", 9, contactData.getPhoneNumber().length());
        ValidationUtils.validateMaxValue("phone number", 12, contactData.getPhoneNumber().length());

        ValidationUtils.validateNotNull("street", contactData.getHomeAddress().getStreet());
        ValidationUtils.validateRegex("street", contactData.getHomeAddress().getStreet(), ADDRESS_STREET_REGEX);
        ValidationUtils.validateMinValue("street", 3, contactData.getHomeAddress().getStreet().length());
        ValidationUtils.validateMaxValue("street", 15, contactData.getHomeAddress().getStreet().length());

        ValidationUtils.validateNotNull("city", contactData.getHomeAddress().getCity());
        ValidationUtils.validateRegex("city", contactData.getHomeAddress().getCity(), ADDRESS_CITY_REGEX);

        ValidationUtils.validateNotNull("houseNumber", contactData.getHomeAddress().getHouseNumber());
        ValidationUtils.validateRegex("houseNumber", contactData.getHomeAddress().getHouseNumber(), ADDRESS_HOUSE_NUMBER_REGEX);
        ValidationUtils.validateMinValue("houseNumber", 1, contactData.getHomeAddress().getHouseNumber().length());
        ValidationUtils.validateMaxValue("houseNumber", 7, contactData.getHomeAddress().getHouseNumber().length());

        ValidationUtils.validateNotNull("zipCode", contactData.getHomeAddress().getZipCode());
        ValidationUtils.validateRegex("zipCode", contactData.getHomeAddress().getZipCode(), ADDRESS_ZIP_CODE_REGEX);

        ValidationUtils.validateNotNull("state", contactData.getHomeAddress().getState());
        ValidationUtils.validateRegex("state", contactData.getHomeAddress().getState(), ADDRESS_STATE_REGEX);
    }
}
