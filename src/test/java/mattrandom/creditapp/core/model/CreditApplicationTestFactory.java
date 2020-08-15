package mattrandom.creditapp.core.model;

import java.time.ZoneId;
import java.util.Set;

import static mattrandom.creditapp.core.Constants.*;

public class CreditApplicationTestFactory {
    public static final ZoneId CLIENT_TIME_ZONE = ZoneId.of("Europe/Warsaw");

    public static CreditApplication create(NaturalPerson person, PurposeOfLoan purposeOfLoan, Set<Guarantor> guarantors) {
        return new CreditApplication(DEFAULT_SYSTEM_LOCALE, CLIENT_TIME_ZONE, person, purposeOfLoan, guarantors);
    }

    public static CreditApplication create(NaturalPerson person, PurposeOfLoan purposeOfLoan) {
        return new CreditApplication(DEFAULT_SYSTEM_LOCALE, CLIENT_TIME_ZONE, person, purposeOfLoan);
    }

    public static CreditApplication create(SelfEmployed person, PurposeOfLoan purposeOfLoan) {
        return new CreditApplication(DEFAULT_SYSTEM_LOCALE, CLIENT_TIME_ZONE, person, purposeOfLoan);
    }

    public static CreditApplication create() {
        NaturalPerson person = PersonTestFactory.create(5000.00, 2, Education.MIDDLE, MaritalStatus.SEPARATED);
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 100000, 35);
        CreditApplication creditApplication = new CreditApplication(DEFAULT_SYSTEM_LOCALE, CLIENT_TIME_ZONE, person, purposeOfLoan);
        return creditApplication;
    }

    public static CreditApplication create(double expectedLoanAmount){
        NaturalPerson person = PersonTestFactory.create(4000, 1, Education.MIDDLE,MaritalStatus.SEPARATED);
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, expectedLoanAmount, 25);
        CreditApplication creditApplication = new CreditApplication(DEFAULT_SYSTEM_LOCALE, CLIENT_TIME_ZONE, person,purposeOfLoan);
        return creditApplication;
    }
}
