package mattrandom.creditapp.core;

import mattrandom.creditapp.core.model.PersonalData;

import java.math.BigDecimal;

public class CreditApplicationDecision {
    private final DecisionType type;
    private final PersonalData personalData;
    private final Double creditRate;
    private final int scoring;

    public CreditApplicationDecision(DecisionType type, PersonalData personalData, Double creditRate, int scoring) {
        this.type = type;
        this.personalData = personalData;
        this.creditRate = creditRate;
        this.scoring = scoring;
    }

    public String getDesisionString() {
        switch (type) {
            case POSITIVE:
                return "Congratulations, " + personalData.getName() + " " + personalData.getLastName() + ", decision is positive.";
            case NEGATIVE_SCORING:
                return "Sorry, " + personalData.getName() + " " + personalData.getLastName() + ", decision is negative.";
            case NEGATIVE_RATING:
                BigDecimal roundedCreditRate = new BigDecimal(creditRate).setScale(2);
                return "Sorry " + personalData.getName() + " " + personalData.getLastName() + ", decision is negative. Bank can borrow only " + roundedCreditRate;
            case CONTACT_REQUIRED:
                return "Sorry, " + personalData.getName() + " " + personalData.getLastName() + ", bank requires additional documents. Our Consultant will contact you.";
            case NEGATIVE_REQUIREMENTS_NOT_MET:
                return "Sorry, " + personalData.getName() + " " + personalData.getLastName() + ", decision is negative. Minimum loan amount for mortgage is " + Constants.MIN_LOAN_AMOUNT_MORTGAGE;
        }
        return null;
    }

    public Double getCreditRate() {
        return creditRate;
    }

    public int getScoring() {
        return scoring;
    }

    public DecisionType getType() {
        return type;
    }
}
