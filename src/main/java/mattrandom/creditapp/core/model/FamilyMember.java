package mattrandom.creditapp.core.model;

import mattrandom.creditapp.core.annotation.NotNull;

public class FamilyMember implements Comparable<FamilyMember> {
    @NotNull
    private final String name;
    @NotNull
    private final Integer age;

    public FamilyMember(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "FamilyMember{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(FamilyMember o) {
        return o.age.compareTo(age);
    }
}
