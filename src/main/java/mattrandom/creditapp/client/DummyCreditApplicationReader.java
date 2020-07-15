package mattrandom.creditapp.client;

import mattrandom.creditapp.core.model.CreditApplication;
import mattrandom.creditapp.core.model.NaturalPerson;
import mattrandom.creditapp.core.model.PurposeOfLoan;
import mattrandom.creditapp.core.model.PurposeOfLoanType;

public class DummyCreditApplicationReader implements CreditApplicationReader {

    @Override
    public CreditApplication read() {
        return new CreditApplication(NaturalPerson.Builder
                .create()
                .build(),
                new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 10000, 30));
    }
}
