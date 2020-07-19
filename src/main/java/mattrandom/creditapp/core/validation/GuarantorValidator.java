package mattrandom.creditapp.core.validation;

import mattrandom.creditapp.core.Constants;
import mattrandom.creditapp.core.exception.ValidationException;
import mattrandom.creditapp.core.model.CreditApplication;
import mattrandom.creditapp.core.model.Guarantor;

import java.util.Set;

import static mattrandom.creditapp.core.Constants.*;

public class GuarantorValidator implements Validator {

    @Override
    public void validate(CreditApplication creditApplication) throws ValidationException {
        final Set<Guarantor> guarantorSet = creditApplication.getGuarantors();
        for (Guarantor g : guarantorSet) {
            ValidationUtils.validateNotNull("guarantorPesel", g.getPesel());
            ValidationUtils.validateRegex("guarantorPesel", g.getPesel(), PESEL_REGEX);
            ValidationUtils.validateMinValue("guarantorAge", 0, g.getAge());
        }
    }
}
