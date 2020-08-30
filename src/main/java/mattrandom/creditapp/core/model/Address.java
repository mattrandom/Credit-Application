package mattrandom.creditapp.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import mattrandom.creditapp.core.Constants;
import mattrandom.creditapp.core.annotation.NotNull;
import mattrandom.creditapp.core.annotation.Regex;

import java.io.Serializable;

public class Address implements Serializable {
    public static final long serialVersionUID = 1L;

    @NotNull
    @JsonProperty
    @Regex(Constants.ADDRESS_STREET_REGEX)
    private String street;

    @NotNull
    @JsonProperty
    @Regex(Constants.ADDRESS_CITY_REGEX)
    private String city;

    @NotNull
    @JsonProperty
    @Regex(Constants.ADDRESS_HOUSE_NUMBER_REGEX)
    private String houseNumber;

    @NotNull
    @JsonProperty
    @Regex(Constants.ADDRESS_ZIP_CODE_REGEX)
    private String zipCode;

    @NotNull
    @JsonProperty
    @Regex(Constants.ADDRESS_STATE_REGEX)
    private String state;

    public Address() {
    }

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
}
