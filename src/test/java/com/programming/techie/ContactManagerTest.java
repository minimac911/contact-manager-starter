package com.programming.techie;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @AfterEach
    public void tearDown() {
        System.out.println("This should execute after each test");
    }

    @AfterAll
    public static void tearDownAll() {
        System.out.println("This should execute after all tests");
    }
}