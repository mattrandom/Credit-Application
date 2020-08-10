package mattrandom.creditapp.core.model;

import mattrandom.creditapp.core.annotation.NotNull;
import mattrandom.creditapp.core.annotation.ValidateCollection;
import mattrandom.creditapp.core.annotation.ValidateObject;

import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

public class CreditApplication {
    @NotNull
    @ValidateObject
    private final Person person;
    @NotNull
    @ValidateObject
    private final PurposeOfLoan purposeOfLoan;
    @NotNull
    private final UUID id;
    @NotNull
    @ValidateCollection
    private final Set<Guarantor> guarantors;

    public CreditApplication(Person person, PurposeOfLoan purposeOfLoan) {
        this.person = person;
        this.purposeOfLoan = purposeOfLoan;
        this.id = UUID.randomUUID();
        this.guarantors = new TreeSet<>();
    }

    public CreditApplication(Person person, PurposeOfLoan purposeOfLoan, Set<Guarantor> guarantors) {
        this.person = person;
        this.purposeOfLoan = purposeOfLoan;
        this.id = UUID.randomUUID();
        this.guarantors = new TreeSet<>(guarantors);
    }

    public Set<Guarantor> getGuarantors() {
        return guarantors;
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
