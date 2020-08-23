package mattrandom.creditapp.core.model;

import mattrandom.creditapp.core.Constants;
import mattrandom.creditapp.core.annotation.NotNull;
import mattrandom.creditapp.core.annotation.Regex;

import java.io.Serializable;

public class Address implements Serializable {
    public static final long serialVersionUID = 1L;
    @NotNull
    @Regex(Constants.ADDRESS_STREET_REGEX)
    private final String street;
    @NotNull
    @Regex(Constants.ADDRESS_CITY_REGEX)
    private final String city;
    @NotNull
    @Regex(Constants.ADDRESS_HOUSE_NUMBER_REGEX)
    private final String houseNumber;
    @NotNull
    @Regex(Constants.ADDRESS_ZIP_CODE_REGEX)
    private final String zipCode;
    @NotNull
    @Regex(Constants.ADDRESS_STATE_REGEX)
    private final String state;

    public Address(String street, String city, String houseNumber, String zipCode, String state) {
        this.street = street;
        this.city = city;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        Address address = (Address) o;
        return street.equalsIgnoreCase(address.street) &&
                city.equalsIgnoreCase(address.city) &&
                houseNumber.equalsIgnoreCase(address.houseNumber) &&
                zipCode.equalsIgnoreCase(address.zipCode) &&
                state.equalsIgnoreCase(address.state);
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getState() {
        return state;
    }
}
