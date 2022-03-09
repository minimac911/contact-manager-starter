package com.programming.techie;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContactManagerTest {

    private ContactManager contactManager;

    @BeforeAll
    public static void setupAll() {
        System.out.println("This should be printed before all");
    }

    @BeforeEach
    public void setup() {
        System.out.println("Initialising the contact manager");
        contactManager = new ContactManager();
    }

    @Test
    @DisplayName("Should create a contact")
    @EnabledOnOs(value = OS.MAC, disabledReason = "This should run only on macs")
    public void shouldCreateContact() {
        contactManager.addContact("Thomas", "Test", "0123456789");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    @Test
    @DisplayName("Should not create contact when the first name is null")
    public void shouldThrowRuntimeExceptionWhenFirstNameIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact(null, "Test", "1234567890");
        });
    }

    @Test
    @DisplayName("Should not create contact when the last name is null")
    public void shouldThrowRuntimeExceptionWhenLastNameIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("Thomas", null, "1234567890");
        });
    }

    @Test
    @DisplayName("Should not create contact when the phone number is null")
    public void shouldThrowRuntimeExceptionWhenPhoneNumberIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("Thomas", "Test", null);
        });
    }

    @Test
    @DisplayName("Test contact creation on the developers machine")
    public void shouldTestContactCreationOnDev() {
        Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")));
        contactManager.addContact("First", "last", "0123456789");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    @DisplayName("Repeat contact creation 5 times")
    @RepeatedTest(5)
    public void shouldTestContactCreationRepeatedly() {
        contactManager.addContact("First", "Last", "0123456789");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class ParameterizedTests {

        @DisplayName("Phone Number should start with 0")
        @ParameterizedTest
        @ValueSource(strings = {"0123456789", "0123456789", "0123456789"})
        public void shouldTestPhoneNumberFormat(String phoneNumber) {
            contactManager.addContact("First", "Last", phoneNumber);
            assertFalse(contactManager.getAllContacts().isEmpty());
            assertEquals(1, contactManager.getAllContacts().size());
        }

        @DisplayName("Method source - Phone Number should start with 0")
        @ParameterizedTest
        @MethodSource("phoneNumbers")
        public void shouldTestPhoneNumberFormatWithMethodSource(String phoneNumber) {
            contactManager.addContact("First", "Last", phoneNumber);
            assertFalse(contactManager.getAllContacts().isEmpty());
            assertEquals(1, contactManager.getAllContacts().size());
        }

        private List<String> phoneNumbers() {
            return Arrays.asList("0123456789", "0123456789", "0123456789");
        }

        @DisplayName("CSV source - Phone Number should start with 0")
        @ParameterizedTest
        @CsvSource({"0123456789", "0123456789", "0123456789"})
        public void shouldTestPhoneNumberFormatWithCSVSource(String phoneNumber) {
            contactManager.addContact("First", "Last", phoneNumber);
            assertFalse(contactManager.getAllContacts().isEmpty());
            assertEquals(1, contactManager.getAllContacts().size());
        }

        @DisplayName("CSV file source - Phone Number should start with 0")
        @ParameterizedTest
        @CsvFileSource(resources = "/data.csv")
        public void shouldTestPhoneNumberFormatWithCSVFileSource(String phoneNumber) {
            contactManager.addContact("First", "Last", phoneNumber);
            assertFalse(contactManager.getAllContacts().isEmpty());
            assertEquals(1, contactManager.getAllContacts().size());
        }
    }

    @Test
    @DisplayName("Test should be disabled")
    @Disabled
    public void shouldBeDisabled() {
        throw new RuntimeException("Test should be disabled");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("This should execute after each test");
    }

    @AfterAll
    public static void tearDownAll() {
        System.out.println("This should execute after all tests");
    }
}