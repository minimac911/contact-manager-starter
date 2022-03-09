package com.programming.techie;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;

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

    @AfterEach
    public void tearDown() {
        System.out.println("This should execute after each test");
    }

    @AfterAll
    public static void tearDownAll() {
        System.out.println("This should execute after all tests");
    }
}