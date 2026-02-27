package contact;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;


/**
 * Managing Contact objects service class.
 * Give in-memory storage with CRUD operations.
 * Requirements: Adding contacts with unique ID, Deleting contacts by ID, udating the contact fields using firstName, lastName, phone, and address
 */
public class ContactService {
    
    private final Map<String, Contact> contacts = new HashMap<>();
    

    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact should not be null")
        }
        
        String id = contact.getContactId();
        
        if (contacts.containsKey(id)) {
            throw new IllegalArgumentException("ID exists: " + id);
        }
        
        contacts.put(id, contact);
    }
    

    public void deleteContact(String contactId) {
        if (contactId == null) {
            throw new IllegalArgumentException("Contact ID should not be null");
        }
        
        if (!contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("Not found: " + contactId);
        }
        
        contacts.remove(contactId);
    }

    public void updateFirstName(String contactId, String firstName) {
        Contact contact = getContact(contactId);
        if (contact == null) {
            throw new IllegalArgumentException("Not found: " + contactId);
        }
        contact.setFirstName(firstName);
    }
    

    public void updateLastName(String contactId, String lastName) {
        Contact contact = getContact(contactId);
        if (contact == null) {
            throw new IllegalArgumentException("Not found: " + contactId);
        }
        contact.setLastName(lastName);
    }
    

    public void updatePhone(String contactId, String phone) {
        Contact contact = getContact(contactId);
        if (contact == null) {
            throw new IllegalArgumentException("Not found: " + contactId);
        }
        contact.setPhone(phone);
    }
    

    public void updateAddress(String contactId, String address) {
        Contact contact = getContact(contactId);
        if (contact == null) {
            throw new IllegalArgumentException("Not found: " + contactId);
        }
        contact.setAddress(address);
    }
    

    public Contact getContact(String contactId) {
        if (contactId == null) {
            return null;
        }
        return contacts.get(contactId);
    }
    
    //returns all IDs

    public Set<String> getAllContactIds() {
        return new HashSet<>(contacts.keySet());
    }
    
    //returns Numbers

    public int getContactCount() {
        return contacts.size();
    }
    

    public boolean contactExists(String contactId) {
        if (contactId == null) {
            return false;
        }
        return contacts.containsKey(contactId);
    }
}