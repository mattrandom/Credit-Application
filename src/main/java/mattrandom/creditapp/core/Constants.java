package mattrandom.creditapp.core;

public class Constants {
    public static final double MORTGAGE_LOAN_RATE;
    public static final double PERSONAL_LOAN_LOAN_RATE;
    public static final String DOUBLE_REGEX;
    public static final String INTEGER_REGEX;
    public static final String NAME_REGEX;
    public static final String LAST_NAME_REGEX;
    public static final String EMAIL_REGEX;
    public static final String PHONE_REGEX;
    public static final double MIN_LOAN_AMOUNT_MORTGAGE;
    public static final String ADDRESS_STREET_REGEX;
    public static final String ADDRESS_CITY_REGEX;
    public static final String ADDRESS_STATE_REGEX;
    public static final String ADDRESS_ZIP_CODE_REGEX;
    public static final String ADDRESS_HOUSE_NUMBER_REGEX;

    static {
        MORTGAGE_LOAN_RATE = 0.2;
        PERSONAL_LOAN_LOAN_RATE = 0.1;
        DOUBLE_REGEX = "(\\d+)(\\.\\d+)?";
        INTEGER_REGEX = "\\d+";
        NAME_REGEX = "[A-ZĄ-Ź][a-zą-ź]{2,9}";
        LAST_NAME_REGEX = "([A-ZĄ-Ź][a-zą-ź]+)([\\s-]([A-ZĄ-Ź][a-zą-ź]+))?";
        EMAIL_REGEX = ".+@.+";
        PHONE_REGEX = "(\\+\\d{2})?\\d{9}";
        MIN_LOAN_AMOUNT_MORTGAGE = 100000.00;
        ADDRESS_STREET_REGEX = "[A-ZĄ-Ź][a-zą-ź]{2,14}";
        ADDRESS_CITY_REGEX = "([A-ZĄ-Ź][a-zą-ź]+)([\\s-]([A-ZĄ-Ź][a-zą-ź]+))?";
        ADDRESS_STATE_REGEX = "([A-ZĄ-Ź][a-zą-ź]+)([\\s-]([A-ZĄ-Ź][a-zą-ź]+))?";
        ADDRESS_ZIP_CODE_REGEX = "(\\d){2}[-]{1}(\\d){3}";
        ADDRESS_HOUSE_NUMBER_REGEX = "(\\d){1,3}([\\/]{1}(\\d){1,3})?";
    }
}
