package app.boot.boottest.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContactManagerTest {

    ContactManager contactManager;

    @BeforeAll
    void setUpAll() {
    //    contactManager = new ContactManager();
        System.out.println("Before All");
    }

    @BeforeEach
    void setup() {
        System.out.println("before each");
        contactManager = new ContactManager();
    }

    @Test
    void addContact() {
        contactManager.addContact("sud", "kas", "9876543210");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    @Test
    @DisplayName("Should not create contact when firstName is null")
    void testShouldThrowWhenFirstNameIsNull() {
        assertThrows(RuntimeException.class,
            () -> contactManager.addContact(null, "kas", "9876543210"));
    }

    @Test
    @DisplayName("Should not create contact when lastName is empty")
    void testShouldThrowWhenLastNameIsEmpty() {
        assertThrows(RuntimeException.class,
            () -> contactManager.addContact("Sud", "", "9876543210"));
    }

    @Test
    @DisplayName("Should not create contact when phoneNumber is null")
    void testShouldThrowWhenPhoneNumberIsNull() {
        assertThrows(RuntimeException.class,
            () -> contactManager.addContact("Sud", "kas", null));
    }

    @RepeatedTest(value = 10, name = "Repeated contact creation test {currentRepetition} of {totalRepetitions}")
    void repeatedAddContact() {
        contactManager.addContact("sud", "kas", "9876543210");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    @DisplayName("Parameterized Test")
    @ParameterizedTest
    @ValueSource(strings = {"9876543210","2345678901", "7658439210"})
    void parameterizedAddContact(String phoneNum) {
        contactManager.addContact("sud", "kas", phoneNum);
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

  @DisplayName("Parameterized Test method source")
    @ParameterizedTest
    @MethodSource("phoneNumberList")
    void parameterizedMethodSourceAddContact(String phoneNum) {
        contactManager.addContact("sud", "kas", phoneNum);
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    List<String> phoneNumberList() {
        return List.of("9876543210","2345678901", "7658439210");
    }


    @AfterEach
    void tearDown() {
        System.out.println("After each");
    }

    @AfterAll
    void tearDownAll() {
        System.out.println("After ALl");
    }
}