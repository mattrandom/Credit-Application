package mattrandom.creditapp.client;

import mattrandom.creditapp.core.Constants;
import mattrandom.creditapp.core.model.*;

import java.time.ZoneId;
import java.util.Scanner;

import static mattrandom.creditapp.core.Constants.*;

public class ConsoleReader implements CreditApplicationReader {

    @Override
    public CreditApplication read() {
        Scanner in = new Scanner(System.in);

        String name = getName(in);
        String lastName = getLastName(in);
        String mothersMaidenName = getMothersMaidenName(in);
        MaritalStatus maritalStatus = getMaritalStatus(in);
        Education education = getEducation(in);
        String email = getEmail(in);
        String phoneNumber = getPhoneNumber(in);
        SourceOfIncome[] sourcesOfIncome = getSourceOfIncomes(in);
        int numOfDependant = getNumOfDependants(in);
        PurposeOfLoanType purposeOfLoanType = getPurposeOfLoanType(in);
        double purposeOfLoanAmount = getPurposeOfLoanAmount(in);
        int period = getPeriod(in);
        String city = getCityAddress(in);
        String zipCode = getZipCodeAddress(in);
        String state = getStateAddress(in);
        String street = getStreetAddress(in);
        String houseNumber = getHouseNumber(in);
        String corespondCity = getCorrespondenceCityAddress(in);
        String corespondZipCode = getCorrespondenceZipCodeAddress(in);
        String corespondState = getCorrespondenceStateAddress(in);
        String corespondStreet = getCorrespondenceStreetAddress(in);
        String corespondHouseNumber = getCorrespondenceHouseNumber(in);

        ContactData contactData = ContactData.Builder
                .create()
                .withEmail(email)
                .withPhoneNumber(phoneNumber)
                .withHomeAddress(new Address(street, city, houseNumber, zipCode, state))
                .withCorrespondenceAddress(new Address(corespondStreet, corespondCity, corespondHouseNumber, corespondZipCode, corespondState))
                .build();

        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(purposeOfLoanType, purposeOfLoanAmount, period);
        FinanceData financeData = new FinanceData(sourcesOfIncome);

        return new CreditApplication(
                DEFAULT_SYSTEM_LOCALE,
                ZoneId.of("Europe/Warsaw"),
                NaturalPerson.Builder
                .create()
                .withContactData(contactData)
                .withFinanceData(financeData)
                .withPersonalData(PersonalData.Builder
                        .create()
                        .withName(name)
                        .withLastName(lastName)
                        .withMothersMaidenName(mothersMaidenName)
                        .withMaritalStatus(maritalStatus)
                        .withEducation(education)
                        .build())
                .build(),
                purposeOfLoan);
    }

    private String getCorrespondenceHouseNumber(Scanner in) {
        String input;
        do {
            System.out.println("Enter house number of the correspondence address:");
            input = in.next();
        } while (!StringValidator.validateString(input, ADDRESS_HOUSE_NUMBER_REGEX));
        return input;
    }

    private String getCorrespondenceZipCodeAddress(Scanner in) {
        String input;
        do {
            System.out.println("Enter ZIP-CODE of the correspondence address:");
            input = in.next();
        } while (!StringValidator.validateString(input, ADDRESS_ZIP_CODE_REGEX));
        return input;
    }

    private String getCorrespondenceStateAddress(Scanner in) {
        String input;
        do {
            System.out.println("Enter state of the correspondence address:");
            input = in.next();
        } while (!StringValidator.validateString(input, ADDRESS_STATE_REGEX));
        return input;
    }

    private String getCorrespondenceCityAddress(Scanner in) {
        String input;
        do {
            System.out.println("Enter city of the correspondence address:");
            input = in.next();
        } while (!StringValidator.validateString(input, ADDRESS_CITY_REGEX));
        return input;
    }

    private String getCorrespondenceStreetAddress(Scanner in) {
        String input;
        do {
            System.out.println("Enter street of the correspondence address:");
            input = in.next();
        } while (!StringValidator.validateString(input, ADDRESS_STREET_REGEX));
        return input;
    }

    private String getHouseNumber(Scanner in) {
        String input;
        do {
            System.out.println("Enter the house number of your residence:");
            input = in.next();
        } while (!StringValidator.validateString(input, ADDRESS_HOUSE_NUMBER_REGEX));
        return input;
    }

    private String getZipCodeAddress(Scanner in) {
        String input;
        do {
            System.out.println("Enter the ZIP-CODE of your city:");
            input = in.next();
        } while (!StringValidator.validateString(input, ADDRESS_ZIP_CODE_REGEX));
        return input;
    }

    private String getStateAddress(Scanner in) {
        String input;
        do {
            System.out.println("Enter the state of residence:");
            input = in.next();
        } while (!StringValidator.validateString(input, ADDRESS_STATE_REGEX));
        return input;
    }

    private String getCityAddress(Scanner in) {
        String input;
        do {
            System.out.println("Enter the city of residence:");
            input = in.next();
        } while (!StringValidator.validateString(input, ADDRESS_CITY_REGEX));
        return input;
    }

    private String getStreetAddress(Scanner in) {
        String input;
        do {
            System.out.println("Enter your street name address:");
            input = in.next();
        } while (!StringValidator.validateString(input, ADDRESS_STREET_REGEX));
        return input;
    }

    private int getPeriod(Scanner in) {
        String input;
        do {
            System.out.println("Enter loan period (in years):");
            input = in.next();
        } while (!NumberValidator.validateInteger(input, 5, 10, 15, 20, 25, 30));
        return Integer.valueOf(input);
    }

    private double getPurposeOfLoanAmount(Scanner in) {
        String input;
        do {
            System.out.println("Enter loan amount:");
            input = in.next();
        } while (!NumberValidator.validateDouble(input, 0.0, Double.MAX_VALUE));
        return Double.valueOf(input);
    }

    private int getNumOfDependants(Scanner in) {
        String input;
        do {
            System.out.println("Enter number of family dependants (including applicant): ");
            input = in.next();
        } while (!NumberValidator.validateInteger(input, 1, Integer.MAX_VALUE));
        return Integer.valueOf(input);
    }

    private SourceOfIncome[] getSourceOfIncomes(Scanner in) {
        int numOfSourcesOfIncome = getNumOfSourcesOfIncome(in);
        SourceOfIncome[] sourcesOfIncome = new SourceOfIncome[numOfSourcesOfIncome];
        for (int i = 1; i <= numOfSourcesOfIncome; i++) {
            IncomeType incomeType = getIncomeType(in, i);
            double netMonthlyIncome = getNetMonthlyIncome(in, i);
            SourceOfIncome sourceOfIncome = new SourceOfIncome(incomeType, netMonthlyIncome);
            sourcesOfIncome[i - 1] = sourceOfIncome;
        }
        return sourcesOfIncome;
    }

    private double getNetMonthlyIncome(Scanner in, int i) {
        String input;
        do {
            System.out.println("Enter net monthly income of source of income " + i + " :");
            input = in.next();
        } while (!NumberValidator.validateDouble(input, 0.0, Double.MAX_VALUE));
        return Double.valueOf(input);
    }

    private int getNumOfSourcesOfIncome(Scanner in) {
        String input;
        do {
            System.out.println("How many sources of income do you have? ");
            input = in.next();
        } while (!NumberValidator.validateInteger(input, 0, Integer.MAX_VALUE));
        return Integer.valueOf(input);
    }

    private String getPhoneNumber(Scanner in) {
        String input;
        do {
            System.out.println("Enter your phone number:");
            input = in.next();
        } while (!PhoneValidator.validate(input));
        return input;
    }

    private String getEmail(Scanner in) {
        String input;
        do {
            System.out.println("Enter your e-mail address:");
            input = in.next();
        } while (!StringValidator.validateString(input, EMAIL_REGEX));
        return input;
    }

    private String getMothersMaidenName(Scanner in) {
        String input;
        do {
            System.out.println("Enter your mothers maiden name: ");
             input = in.next();
        } while (!StringValidator.validateString(input, LAST_NAME_REGEX));
        return input;
    }

    private String getLastName(Scanner in) {
        String input;
        do {
            System.out.println("Enter your last name: ");
            input = in.next();
        } while (!StringValidator.validateString(input, LAST_NAME_REGEX));
        return input;
    }

    private String getName(Scanner in) {
        String input;
        do {
            System.out.println("Enter your name: ");
            input = in.next();
        } while (!StringValidator.validateString(input, NAME_REGEX));
        return input;
    }


    private PurposeOfLoanType getPurposeOfLoanType(Scanner in) {
        String purposeOfLoanInput;
        do {
            System.out.println("What is purpose of loan? " + generatePurposeOfLoanTypeElements());
            purposeOfLoanInput = in.next();
        } while (!EnumValidator.validatePurposeOfLoanType(purposeOfLoanInput));
        return PurposeOfLoanType.valueOf(purposeOfLoanInput);
    }

    private IncomeType getIncomeType(Scanner in, int i) {
        String incomeTypeInput;
        do {
            System.out.println("Enter type of source of income " + i + generateIncomeTypeElements());
            incomeTypeInput = in.next();
        } while (!EnumValidator.validateIncomeType(incomeTypeInput));
        return IncomeType.valueOf(incomeTypeInput);
    }

    private Education getEducation(Scanner in) {
        String educationInput;
        do {
            System.out.println("What is your education level? " + generateEducationElements());
            educationInput = in.next();
        } while (!EnumValidator.validateEducation(educationInput));
        return Education.valueOf(educationInput);
    }

    private MaritalStatus getMaritalStatus(Scanner in) {
        String maritalStatusInput;
        do {
            System.out.println("What is your marital status? " + generateMaritalStatusElements());
            maritalStatusInput = in.next();
        } while (!EnumValidator.validateMaritalStatus(maritalStatusInput));
        return MaritalStatus.valueOf(maritalStatusInput);
    }

    private String generatePurposeOfLoanTypeElements() {
        String elements = "(";
        for (int i = 0; i < PurposeOfLoanType.values().length; i++) {
            elements += PurposeOfLoanType.values()[i].name();
            if (i < PurposeOfLoanType.values().length - 1) {
                elements += ", ";
            }
        }
        elements += ")";
        return elements;
    }

    private String generateIncomeTypeElements() {
        String elements = "(";
        for (int i = 0; i < IncomeType.values().length; i++) {
            elements += IncomeType.values()[i].name();
            if (i < IncomeType.values().length - 1) {
                elements += ", ";
            }
        }
        elements += ")";
        return elements;
    }

    private String generateEducationElements() {
        String elements = "(";
        for (int i = 0; i < Education.values().length; i++) {
            elements += Education.values()[i].name();
            if (i < Education.values().length -1) {
                elements += ", ";
            }
        }
        elements += ")";
        return elements;
    }

    private String generateMaritalStatusElements() {
        String elements = "(";
        for (int i = 0; i < MaritalStatus.values().length; i++) {
            elements += MaritalStatus.values()[i].name();
            if (i < MaritalStatus.values().length - 1) {
                elements += ", ";
            }
        }
        elements += ")";
        return elements;
    }
}
