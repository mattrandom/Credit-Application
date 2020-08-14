package mattrandom.creditapp.core.model;

import java.util.ArrayList;
import java.util.List;

import static mattrandom.creditapp.util.AgeUtils.generateBirthDate;

public class PersonTestFactory {

    public static NaturalPerson create(int numOfDependants, SourceOfIncome... sourcesOfIncomes) {
        List<FamilyMember> familyMemberList = getFamilyMembers(numOfDependants);
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withMaritalStatus(MaritalStatus.SINGLE)
                .withEducation(Education.MIDDLE)
                .build();
        return NaturalPerson.Builder
                .create()
                .withPersonalData(personalData)
                .withFamilyMembers(familyMemberList)
                .withFinanceData(new FinanceData(sourcesOfIncomes))
                .build();
    }

    private static List<FamilyMember> getFamilyMembers(int numOfDependants) {
        List<FamilyMember> familyMemberList = new ArrayList<>();
        for (int i = 0; i < numOfDependants - 1; i++) {
            familyMemberList.add(new FamilyMember("Mateo", generateBirthDate(18)));
        }
        return familyMemberList;
    }

    public static NaturalPerson create(MaritalStatus maritalStatus) {
        List<FamilyMember> familyMemberList = getFamilyMembers(1);
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withMaritalStatus(maritalStatus)
                .withEducation(Education.MIDDLE)
                .build();
        return NaturalPerson.Builder
                .create()
                .withFamilyMembers(familyMemberList)
                .withPersonalData(personalData)
                .build();
    }

    public static NaturalPerson create(Education education) {
        List<FamilyMember> familyMemberList = getFamilyMembers(1);
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withMaritalStatus(MaritalStatus.SINGLE)
                .withEducation(education)
                .build();
        return NaturalPerson.Builder
                .create()
                .withFamilyMembers(familyMemberList)
                .withPersonalData(personalData)
                .build();
    }

    public static NaturalPerson create() {
        List<FamilyMember> familyMemberList = getFamilyMembers(1);
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withMaritalStatus(MaritalStatus.SINGLE)
                .withEducation(Education.MIDDLE)
                .build();
        return NaturalPerson.Builder
                .create()
                .withFamilyMembers(familyMemberList)
                .withPersonalData(personalData)
                .build();
    }

    public static NaturalPerson create(double totalMonthlyIncomeInPln, int numOfDependants, Education education, MaritalStatus maritalStatus) {
        List<FamilyMember> familyMemberList = getFamilyMembers(1);
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withMaritalStatus(maritalStatus)
                .withEducation(education)
                .build();
        return NaturalPerson.Builder
                .create()
                .withFamilyMembers(familyMemberList)
                .withPersonalData(personalData)
                .withFinanceData(new FinanceData(new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, totalMonthlyIncomeInPln)))
                .build();
    }
}