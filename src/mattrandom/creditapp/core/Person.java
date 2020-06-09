package mattrandom.creditapp.core;

public class Person {
    private final String name;
    private final String lastName;
    private final String mothersMaidenName;
    private final double totalMonthlyIncomeInPln;
    private final boolean married;
    private final int numOfFamilyDependants;

    public Person(String name, String lastName, String mothersMaidenName, double totalMonthlyIncomeInPln,
                  boolean married, int numOfFamilyDependants) {
        this.name = name;
        this.lastName = lastName;
        this.mothersMaidenName = mothersMaidenName;
        this.totalMonthlyIncomeInPln = totalMonthlyIncomeInPln;
        this.married = married;
        this.numOfFamilyDependants = numOfFamilyDependants;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public double getTotalMonthlyIncomeInPln() {
        return totalMonthlyIncomeInPln;
    }

    public boolean isMarried() {
        return married;
    }

    public int getNumOfFamilyDependants() {
        return numOfFamilyDependants;
    }
}
