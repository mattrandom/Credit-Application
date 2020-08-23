package mattrandom.creditapp.core.model;

import mattrandom.creditapp.core.annotation.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class FamilyMember implements Comparable<FamilyMember>, Serializable {
    public static final long serialVersionUID = 1L;
    @NotNull
    private final String name;
    @NotNull
    private final LocalDate birthDate;

    public FamilyMember(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "FamilyMember{" +
                "name='" + name + '\'' +
                ", birthday=" + birthDate +
                '}';
    }

    @Override
    public int compareTo(FamilyMember o) {
        return o.getAge().compareTo(this.getAge());
    }
}
