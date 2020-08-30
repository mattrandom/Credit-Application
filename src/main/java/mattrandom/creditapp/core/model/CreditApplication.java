package mattrandom.creditapp.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import mattrandom.creditapp.core.annotation.ExactlyOneNotNull;
import mattrandom.creditapp.core.annotation.NotNull;
import mattrandom.creditapp.core.annotation.ValidateCollection;
import mattrandom.creditapp.core.annotation.ValidateObject;

import java.io.Serializable;
import java.nio.file.Path;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

@ExactlyOneNotNull({"naturalPerson", "selfEmployed"})
public class CreditApplication implements Serializable {
    public static final long serialVersionUID = 1L;

    @NotNull
    @JsonIgnore
    private UUID id;

    @JsonIgnore
    private ZonedDateTime creationDateClientZone;

    @JsonProperty
    private Locale clientLocale;

    @JsonProperty
    private ZoneId clientTimeZone;

    @JsonProperty
    @ValidateObject
    private NaturalPerson naturalPerson;

    @JsonProperty
    @ValidateObject
    private SelfEmployed selfEmployed;

    @NotNull
    @JsonProperty
    @ValidateObject
    private PurposeOfLoan purposeOfLoan;

    @NotNull
    @JsonProperty
    @ValidateCollection
    private Set<Guarantor> guarantors;

    public CreditApplication() {
    }

    public CreditApplication(Locale clientLocale, ZoneId clientTimeZone, NaturalPerson person, PurposeOfLoan purposeOfLoan) {
        this.clientLocale = clientLocale;
        this.clientTimeZone = clientTimeZone;
        this.creationDateClientZone = ZonedDateTime.now(clientTimeZone);
        this.naturalPerson = person;
        this.purposeOfLoan = purposeOfLoan;
        this.id = UUID.randomUUID();
        this.guarantors = new TreeSet<>();
    }

    public CreditApplication(Locale clientLocale, ZoneId clientTimeZone, SelfEmployed person, PurposeOfLoan purposeOfLoan) {
        this.clientLocale = clientLocale;
        this.clientTimeZone = clientTimeZone;
        this.creationDateClientZone = ZonedDateTime.now(clientTimeZone);
        this.selfEmployed = person;
        this.purposeOfLoan = purposeOfLoan;
        this.id = UUID.randomUUID();
        this.guarantors = new TreeSet<>();
    }


    public CreditApplication(Locale clientLocale, ZoneId clientTimeZone, NaturalPerson person, PurposeOfLoan purposeOfLoan, Set<Guarantor> guarantors) {
        this.clientLocale = clientLocale;
        this.clientTimeZone = clientTimeZone;
        this.creationDateClientZone = ZonedDateTime.now(clientTimeZone);
        this.naturalPerson = person;
        this.purposeOfLoan = purposeOfLoan;
        this.id = UUID.randomUUID();
        this.guarantors = new TreeSet<>(guarantors);
    }

    public CreditApplication(Locale clientLocale, ZoneId clientTimeZone, SelfEmployed person, PurposeOfLoan purposeOfLoan, Set<Guarantor> guarantors) {
        this.clientLocale = clientLocale;
        this.clientTimeZone = clientTimeZone;
        this.creationDateClientZone = ZonedDateTime.now(clientTimeZone);
        this.selfEmployed = person;
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

    @JsonIgnore
    public Person getPerson() {
        return naturalPerson != null ? naturalPerson : selfEmployed;
    }

    @JsonIgnore
    public boolean isNaturalPerson() {
        return naturalPerson != null;
    }

    public void init() {
        this.id = UUID.randomUUID();
        this.creationDateClientZone = ZonedDateTime.now(clientTimeZone);
    }
}
