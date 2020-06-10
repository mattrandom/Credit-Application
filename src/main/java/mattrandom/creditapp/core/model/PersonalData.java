package mattrandom.creditapp.core.model;

public class PersonalData {
    private final String name;
    private final String lastName;
    private final String mothersMaidenName;
    private final MaritalStatus maritalStatus;
    private final Education education;
    private final double totalMonthlyIncomeInPln;
    private final int numOfFamilyDependants;

    public PersonalData(String name, String lastName, String mothersMaidenName, MaritalStatus maritalStatus,
                        Education education, double totalMonthlyIncomeInPln, int numOfFamilyDependants) {
        this.name = name;
        this.lastName = lastName;
        this.mothersMaidenName = mothersMaidenName;
        this.maritalStatus = maritalStatus;
        this.education = education;
        this.totalMonthlyIncomeInPln = totalMonthlyIncomeInPln;
        this.numOfFamilyDependants = numOfFamilyDependants;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMothersMaidenName() {
        return mothersMaidenName;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public Education getEducation() {
        return education;
    }

    public double getTotalMonthlyIncomeInPln() {
        return totalMonthlyIncomeInPln;
    }

    public int getNumOfFamilyDependants() {
        return numOfFamilyDependants;
    }
}
