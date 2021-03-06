package mattrandom.creditapp.core;

import java.time.ZoneId;
import java.util.Locale;

public interface Constants {
    double MORTGAGE_LOAN_RATE = 0.2;
    double PERSONAL_LOAN_LOAN_RATE = 0.1;
    String DOUBLE_REGEX = "(\\d+)(\\.\\d+)?";
    String INTEGER_REGEX = "\\d+";
    String NAME_REGEX = "[A-ZĄ-Ź][a-zą-ź]{2,9}";
    String LAST_NAME_REGEX = "([A-ZĄ-Ź][a-zą-ź]+)([\\s-]([A-ZĄ-Ź][a-zą-ź]+))?";
    String EMAIL_REGEX = ".+@.+";
    String PHONE_REGEX = "(\\+\\d{2})?\\d{9}";
    double MIN_LOAN_AMOUNT_MORTGAGE = 100000.00;
    String ADDRESS_STREET_REGEX = "[A-ZĄ-Ź][a-zą-ź]{2,14}";
    String ADDRESS_CITY_REGEX = "([A-ZĄ-Ź][a-zą-ź]+)([\\s-]([A-ZĄ-Ź][a-zą-ź]+))?";
    String ADDRESS_STATE_REGEX = "([A-ZĄ-Ź][a-zą-ź]+)([\\s-]([A-ZĄ-Ź][a-zą-ź]+))?";
    String ADDRESS_ZIP_CODE_REGEX = "(\\d){2}[-]{1}(\\d){3}";
    String ADDRESS_HOUSE_NUMBER_REGEX = "(\\d){1,3}([\\/]{1}(\\d){1,3})?";
    String PESEL_REGEX = "(\\d){11}";
    String ARG_INDEX_ONE_REGEX = "[NS]-\\d*";
    ZoneId DEFAULT_SYSTEM_ZONE_ID = ZoneId.of("America/New_York");
    Locale DEFAULT_SYSTEM_LOCALE = Locale.US;
    String BIK_API_ENDPOINT_URL = "https://test-api.javaskills.pl/udemy/bik/scoring";
    String OUTPUT_PATH = System.getProperty("user.dir");
}
