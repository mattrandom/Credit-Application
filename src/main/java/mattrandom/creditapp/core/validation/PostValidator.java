package mattrandom.creditapp.core.validation;

import mattrandom.creditapp.core.exception.RequirementNotMetException;
import mattrandom.creditapp.core.model.CreditApplication;

public interface PostValidator {

    void validate(CreditApplication creditApplication, int scoring, double rating) throws RequirementNotMetException;
}
