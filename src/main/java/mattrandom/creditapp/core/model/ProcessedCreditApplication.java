package mattrandom.creditapp.core.model;

import mattrandom.creditapp.core.CreditApplicationDecision;

import java.io.Serializable;

public class ProcessedCreditApplication implements Serializable {
    public static final long serialVersionUID = 1L;

    private final CreditApplication application;
    private final CreditApplicationDecision decision;

    public ProcessedCreditApplication(CreditApplication application, CreditApplicationDecision decision) {
        this.application = application;
        this.decision = decision;
    }

    public CreditApplication getApplication() {
        return application;
    }

    public CreditApplicationDecision getDecision() {
        return decision;
    }
}
