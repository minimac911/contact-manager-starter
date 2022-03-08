package com.programming.techie;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactManagerTest {

    @Test
    @DisplayName("Should create a contact")
    public void shouldCreateContact() {
        ContactManager contactManager = new ContactManager();
        contactManager.addContact("Thomas", "Test", "0123456789");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }
}