package mattrandom.creditapp.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import mattrandom.creditapp.core.Constants;
import mattrandom.creditapp.core.annotation.NotNull;
import mattrandom.creditapp.core.annotation.Regex;
import mattrandom.creditapp.core.annotation.ValidateObject;

import java.io.Serializable;
import java.util.Optional;

public class ContactData implements Serializable {
    public static final long serialVersionUID = 1L;

    @NotNull
    @JsonProperty
    @Regex(Constants.EMAIL_REGEX)
    private String email;

    @NotNull
    @JsonProperty
    @Regex(Constants.PHONE_REGEX)
    private String phoneNumber;

    @NotNull
    @JsonProperty
    @ValidateObject
    private Address homeAddress;

    @JsonIgnore
    @JsonProperty
    private transient Optional<Address> correspondenceAddress = Optional.empty();

    public ContactData() {
    }

    private ContactData(String email, String phoneNumber, Address homeAddress, Optional<Address> correspondenceAddress) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.homeAddress = homeAddress;
        this.correspondenceAddress = correspondenceAddress;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public Optional<Address> getCorrespondenceAddress() {
        return correspondenceAddress;
    }

    public static class Builder {
        private String email;
        private String phoneNumber;
        private Address homeAddress;
        private Address correspondenceAddress;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withHomeAddress(Address homeAddress) {
            this.homeAddress = homeAddress;
            return this;
        }

        public Builder withCorrespondenceAddress(Address correspondenceAddress) {
            this.correspondenceAddress = correspondenceAddress;
            return this;
        }

        public ContactData build() {
            Optional<Address> correspondenceAddress = this.homeAddress.equals(this.correspondenceAddress) ?
                    Optional.empty() : Optional.ofNullable(this.correspondenceAddress);
            return new ContactData(this.email, this.phoneNumber, this.homeAddress, correspondenceAddress);
        }
    }
}
