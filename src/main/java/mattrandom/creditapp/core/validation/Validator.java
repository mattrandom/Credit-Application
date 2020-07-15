package mattrandom.creditapp.core.validation;

import mattrandom.creditapp.core.exception.ValidationException;
import mattrandom.creditapp.core.model.CreditApplication;

public interface Validator {

     void validate(CreditApplication creditApplication) throws ValidationException;
}
