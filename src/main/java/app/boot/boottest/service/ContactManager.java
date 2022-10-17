package app.boot.boottest.service;

import app.boot.boottest.model.Contact;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContactManager {

    Map<String, Contact> contactMap = new ConcurrentHashMap<>();

    public void addContact(String firstName, String lastName, String phoneNumber) {
        Contact contact = new Contact(firstName, lastName, phoneNumber);
        validateContact(contact);
        checkIfContactAlreadyExist(contact);
        contactMap.put(generateKey(contact), contact);
    }

    public Collection<Contact> getAllContacts() {
        return contactMap.values();
    }

    private void checkIfContactAlreadyExist(Contact contact) {
        if (contactMap.containsKey(generateKey(contact)))
            throw new RuntimeException("Contact Already Exists");
    }

    private void validateContact(Contact contact) {
        contact.validateFirstName();
        contact.validateLastName();
        contact.validatePhoneNumber();
    }

    private String generateKey(Contact contact) {
        return String.format("%s-%s", contact.firstName(), contact.lastName());
    }

}
