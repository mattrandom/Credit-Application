package mattrandom.creditapp.core.validation;

import mattrandom.creditapp.core.exception.ValidationException;
import mattrandom.creditapp.core.model.CreditApplication;

public class CreditApplicationValidator implements Validator {
    private final ObjectValidator objectValidator;

    public CreditApplicationValidator(ObjectValidator objectValidator) {
        this.objectValidator = objectValidator;
    }

    @Override
    public void validate(CreditApplication creditApplication) throws ValidationException {
        try {
            objectValidator.validate(creditApplication);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
