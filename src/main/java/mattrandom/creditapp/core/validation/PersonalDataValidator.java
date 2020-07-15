package mattrandom.creditapp.core.validation;

import mattrandom.creditapp.core.exception.ValidationException;
import mattrandom.creditapp.core.model.CreditApplication;
import mattrandom.creditapp.core.model.PersonalData;

import static mattrandom.creditapp.core.Constants.*;

public class PersonalDataValidator implements Validator {

    @Override
    public void validate(CreditApplication creditApplication) throws ValidationException {
        PersonalData personalData = creditApplication.getPerson().getPersonalData();

        ValidationUtils.validateNotNull("name", personalData.getName());
        ValidationUtils.validateRegex("name", personalData.getName(), NAME_REGEX);

        ValidationUtils.validateNotNull("lastName", personalData.getLastName());
        ValidationUtils.validateRegex("lastName", personalData.getLastName(), LAST_NAME_REGEX);

        ValidationUtils.validateNotNull("mothersMaidenName", personalData.getMothersMaidenName());
        ValidationUtils.validateRegex("mothersMaidenName", personalData.getMothersMaidenName(), LAST_NAME_REGEX);

        ValidationUtils.validateNotNull("maritalStatus", personalData.getMaritalStatus());
        ValidationUtils.validateNotNull("education", personalData.getEducation());

        ValidationUtils.validateMinValue("numOfDependants", 0, personalData.getNumOfFamilyDependants());

    }
}
