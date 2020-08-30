package mattrandom.creditapp.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import mattrandom.creditapp.core.annotation.NotNull;
import mattrandom.creditapp.core.annotation.ValidateCollection;
import mattrandom.creditapp.core.annotation.ValidateObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Person implements Serializable {
    public static final long serialVersionUID = 1L;

    @NotNull
    @JsonProperty
    @ValidateObject
    private PersonalData personalData;

    @NotNull
    @JsonProperty
    @ValidateObject
    private ContactData contactData;

    @NotNull
    @JsonProperty
    @ValidateObject
    private FinanceData financeData;

    @NotNull
    @JsonProperty
    @ValidateCollection
    private List<FamilyMember> familyMembers;

    public Person() {
    }

    protected Person(PersonalData personalData, ContactData contactData, FinanceData financeData, List<FamilyMember> familyMembers) {
        this.personalData = personalData;
        this.contactData = contactData;
        this.financeData = financeData;
        this.familyMembers = familyMembers;
        Collections.sort(this.familyMembers);
    }

    public List<FamilyMember> getFamilyMembersSortedByName() {
        List<FamilyMember> copy = new ArrayList<>(this.familyMembers);
        Collections.sort(copy, new FamilyMemberNameComparator());
        return copy;
    }

    public List<FamilyMember> getFamilyMembers() {
        return familyMembers;
    }

    public double getBalance() {
        double totalMonthlyIncome = 0.0;
        for (SourceOfIncome sourceOfIncome : financeData.getSourcesOfIncome()) {
            totalMonthlyIncome += sourceOfIncome.getNetMonthlyIncome();
        }
        double totalExpenses = 0.0;
        for (Expense expense : financeData.getExpenses()) {
            totalExpenses += expense.getAmount();
        }
        return totalMonthlyIncome - totalExpenses;
    }

    public int getNumOfFamilyDependants() {
        return 1 + this.familyMembers.size();
    }

    public FinanceData getFinanceData() {
        return financeData;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public ContactData getContactData() {
        return contactData;
    }

    public double getIncomePerFamilyMember() {
        return getBalance() / this.getNumOfFamilyDependants();
    }
}
