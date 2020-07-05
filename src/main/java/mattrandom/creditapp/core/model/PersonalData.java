package mattrandom.creditapp.core.model;

public class PersonalData {
    private final String name;
    private final String lastName;
    private final String mothersMaidenName;
    private final MaritalStatus maritalStatus;
    private final Education education;
    private final int numOfFamilyDependants;

    private PersonalData(String name, String lastName, String mothersMaidenName, MaritalStatus maritalStatus,
                        Education education, int numOfFamilyDependants) {
        this.name = name;
        this.lastName = lastName;
        this.mothersMaidenName = mothersMaidenName;
        this.maritalStatus = maritalStatus;
        this.education = education;
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

    public int getNumOfFamilyDependants() {
        return numOfFamilyDependants;
    }

    public static class Builder {
        private String name;
        private String lastName;
        private String mothersMaidenName;
        private MaritalStatus maritalStatus;
        private Education education;
        private int numOfFamilyDependants;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withMothersMaidenName(String mothersMaidenName) {
            this.mothersMaidenName = mothersMaidenName;
            return this;
        }

        public Builder withMaritalStatus(MaritalStatus maritalStatus) {
            this.maritalStatus = maritalStatus;
            return this;
        }

        public Builder withEducation(Education education) {
            this.education = education;
            return this;
        }

        public Builder withNumOfFamilyDependants(int numOfFamilyDependants) {
            this.numOfFamilyDependants = numOfFamilyDependants;
            return this;
        }

        public PersonalData build() {
            return new PersonalData(name, lastName, mothersMaidenName, maritalStatus, education, numOfFamilyDependants);
        }
    }
}
