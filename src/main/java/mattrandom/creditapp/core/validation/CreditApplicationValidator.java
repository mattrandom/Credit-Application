package mattrandom.creditapp.core.validation;

import mattrandom.creditapp.core.exception.ValidationException;
import mattrandom.creditapp.core.model.CreditApplication;
import mattrandom.creditapp.di.Inject;

public class CreditApplicationValidator implements Validator {

    @Inject
    private ObjectValidator objectValidator;

    public CreditApplicationValidator(ObjectValidator objectValidator) {
        this.objectValidator = objectValidator;
    }

    public CreditApplicationValidator() {
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
