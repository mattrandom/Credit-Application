package mattrandom.creditapp.core.model;

import mattrandom.creditapp.core.annotation.NotNull;
import mattrandom.creditapp.core.annotation.ValidateCollection;
import mattrandom.creditapp.core.annotation.ValidateObject;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

public class CreditApplication implements Serializable {
    public static final long serialVersionUID = 1L;
    private final Locale clientLocale;
    private final ZoneId clientTimeZone;
    private final ZonedDateTime creationDateClientZone;
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

    public CreditApplication(Locale clientLocale, ZoneId clientTimeZone, Person person, PurposeOfLoan purposeOfLoan) {
        this.clientLocale = clientLocale;
        this.clientTimeZone = clientTimeZone;
        this.creationDateClientZone = ZonedDateTime.now(clientTimeZone);
        this.person = person;
        this.purposeOfLoan = purposeOfLoan;
        this.id = UUID.randomUUID();
        this.guarantors = new TreeSet<>();
    }

    public CreditApplication(Locale clientLocale, ZoneId clientTimeZone, Person person, PurposeOfLoan purposeOfLoan, Set<Guarantor> guarantors) {
        this.clientLocale = clientLocale;
        this.clientTimeZone = clientTimeZone;
        this.creationDateClientZone = ZonedDateTime.now(clientTimeZone);
        this.person = person;
        this.purposeOfLoan = purposeOfLoan;
        this.id = UUID.randomUUID();
        this.guarantors = new TreeSet<>(guarantors);
    }

    public Locale getClientLocale() {
        return clientLocale;
    }

    public ZoneId getClientTimeZone() {
        return clientTimeZone;
    }

    public ZonedDateTime getCreationDateClientZone() {
        return creationDateClientZone;
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
