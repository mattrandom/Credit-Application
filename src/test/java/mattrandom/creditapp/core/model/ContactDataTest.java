package mattrandom.creditapp.core.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactDataTest {

    @Test
    @DisplayName("should set Optional.empty correspondence address, when home address is the same")
    public void test1() {
        // given & when
        ContactData contactData = ContactData.Builder
                .create()
                .withHomeAddress(new Address("Randomowa", "Poznań", "99", "66-666", "Wielkopolska"))
                .withCorrespondenceAddress(new Address("Randomowa", "Poznań", "99", "66-666", "Wielkopolska"))
                .build();

        // then
        assertTrue(contactData.getCorrespondenceAddress().isEmpty());
    }

    @Test
    @DisplayName("should set Optional.of correspondence address, when home address is NOT the same")
    public void test2() {
        // given
        final Address homeAddress = new Address("Randomowa", "Poznań", "99", "66-666", "Wielkopolska");
        final Address correspondenceAddress = new Address("Other", "Poznań", "66", "99-999", "Wielkopolska");

        // when
        ContactData contactData = ContactData.Builder
                .create()
                .withHomeAddress(homeAddress)
                .withCorrespondenceAddress(correspondenceAddress)
                .build();

        // then
        assertTrue(contactData.getCorrespondenceAddress().isPresent());
        assertEquals(correspondenceAddress, contactData.getCorrespondenceAddress().get());
    }
}
