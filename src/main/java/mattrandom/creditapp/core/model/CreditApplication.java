package mattrandom.creditapp.core.model;

import java.util.UUID;

public class CreditApplication {
    private final Person person;
    private final PurposeOfLoan purposeOfLoan;
    private final UUID id;

    public CreditApplication(Person person, PurposeOfLoan purposeOfLoan) {
        this.person = person;
        this.purposeOfLoan = purposeOfLoan;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public PurposeOfLoan getPurposeOfLoan() {
        return purposeOfLoan;
    }

    public Person getPerson() {
        return person;
    }
}
