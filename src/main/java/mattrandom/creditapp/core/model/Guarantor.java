package mattrandom.creditapp.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import mattrandom.creditapp.core.Constants;
import mattrandom.creditapp.core.annotation.NotNull;
import mattrandom.creditapp.core.annotation.Regex;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Guarantor implements Comparable<Guarantor>, Serializable {
    public static final long serialVersionUID = 1L;

    @NotNull
    @JsonProperty
    @Regex(Constants.PESEL_REGEX)
    private String pesel;

    @NotNull
    @JsonProperty
    private LocalDate birthDate;

    public Guarantor() {
    }

    public Guarantor(String pesel, LocalDate birthDate) {
        this.pesel = pesel;
        this.birthDate = birthDate;
    }

    public String getPesel() {
        return pesel;
    }

    public Integer getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guarantor guarantor = (Guarantor) o;
        return pesel.equals(guarantor.pesel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pesel);
    }

    @Override
    public int compareTo(Guarantor g) {
        if (g.pesel.compareTo(this.pesel) != 0) {
            return g.pesel.compareTo(this.pesel);
        }
        return this.birthDate.compareTo(g.birthDate);
    }

    public static class Builder {
        private String pesel;
        private LocalDate birthDate;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withPesel(String pesel) {
            this.pesel = pesel;
            return this;
        }

        public Builder withAge(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Guarantor build() {
            return new Guarantor(pesel, birthDate);
        }
    }
}
