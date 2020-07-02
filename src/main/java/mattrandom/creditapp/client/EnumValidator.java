package mattrandom.creditapp.client;

import mattrandom.creditapp.core.model.Education;
import mattrandom.creditapp.core.model.IncomeType;
import mattrandom.creditapp.core.model.MaritalStatus;
import mattrandom.creditapp.core.model.PurposeOfLoanType;

public class EnumValidator {

    public static boolean validateMaritalStatus(String maritalStatusStr) {
        for (MaritalStatus maritalStatus : MaritalStatus.values()) {
            if (maritalStatus.name().equals(maritalStatusStr)) {
                return true;
            }
        }
        return false;
    }

    public static boolean validateEducation(String educationStr) {
        for (Education education : Education.values()) {
            if (education.name().equals(educationStr)) {
                return true;
            }
        }
        return false;
    }

    public static boolean validateIncomeType(String incomeTypeStr) {
        for (IncomeType incomeType : IncomeType.values()) {
            if (incomeType.name().equals(incomeTypeStr)) {
                return true;
            }
        }
        return false;
    }

    public static boolean validatePurposeOfLoanType(String purposeOfLoanStr) {
        for (PurposeOfLoanType purposeOfLoanType : PurposeOfLoanType.values()) {
            if (purposeOfLoanType.name().equals(purposeOfLoanStr)) {
                return true;
            }
        }
        return false;
    }
}
