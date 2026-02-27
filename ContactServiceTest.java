package contact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//ContactService class unit tests.

class ContactServiceTest {
    
    private ContactService service;
    
    private static final String VALID_ID = "47216";
    private static final String VALID_FIRST_NAME = "Amara";
    private static final String VALID_LAST_NAME = "Camrara";
    private static final String VALID_PHONE = "4721667890";
    private static final String VALID_ADDRESS = "43 Abbott St";
    
    @BeforeEach
    void setUp() {
        service = new ContactService();
    }
    
    private Contact createValidContact() {
        return new Contact(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, 
                          VALID_PHONE, VALID_ADDRESS);
    }
    
    private Contact createValidContact(String id) {
        return new Contact(id, VALID_FIRST_NAME, VALID_LAST_NAME, 
                          VALID_PHONE, VALID_ADDRESS);
    }
    
    //Adding Contact     
    @Test
    void testAddContact() {
        Contact contact = createValidContact();
        service.addContact(contact);
        
        assertEquals(contact, service.getContact(VALID_ID));
        assertEquals(1, service.getContactCount());
        assertTrue(service.contactExists(VALID_ID));
    }
    
    @Test
    void testAddNullContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.addContact(null);
        });
    }
    
    @Test
    void testAddDuplicateContact() {
        Contact contact1 = createValidContact("ID1");
        Contact contact2 = createValidContact("ID1");
        
        service.addContact(contact1);
        
        assertThrows(IllegalArgumentException.class, () -> {
            service.addContact(contact2);
        });
        
        assertEquals(contact1, service.getContact("ID1"));
        assertEquals(1, service.getContactCount());
    }
    
    @Test
    void testAddMultipleContacts() {
        Contact contact1 = createValidContact("ID1");
        Contact contact2 = createValidContact("ID2");
        Contact contact3 = createValidContact("ID3");
        
        service.addContact(contact1);
        service.addContact(contact2);
        service.addContact(contact3);
        
        assertEquals(3, service.getContactCount());
        assertEquals(contact1, service.getContact("ID1"));
        assertEquals(contact2, service.getContact("ID2"));
        assertEquals(contact3, service.getContact("ID3"));
    }
    
    // Deleting Contact    
    @Test
    void testDeleteContact() {
        Contact contact = createValidContact();
        service.addContact(contact);
        
        assertTrue(service.contactExists(VALID_ID));
        
        service.deleteContact(VALID_ID);
        
        assertNull(service.getContact(VALID_ID));
        assertFalse(service.contactExists(VALID_ID));
        assertEquals(0, service.getContactCount());
    }
    
    @Test
    void testDeleteNonExistentContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteContact("NONEXISTENT");
        });
    }
    
    @Test
    void testDeleteContactNullId() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteContact(null);
        });
    }
    
    @Test
    void testDeleteContactTwice() {
        Contact contact = createValidContact();
        service.addContact(contact);
        service.deleteContact(VALID_ID);
        
        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteContact(VALID_ID);
        });
    }
    
    //Updating Contact  
    @Test
    void testUpdateFirstName() {
        Contact contact = createValidContact();
        service.addContact(contact);
        
        service.updateFirstName(VALID_ID, "Jane");
        assertEquals("Jane", service.getContact(VALID_ID).getFirstName());
    }
    
    @Test
    void testUpdateLastName() {
        Contact contact = createValidContact();
        service.addContact(contact);
        
        service.updateLastName(VALID_ID, "Smith");
        assertEquals("Smith", service.getContact(VALID_ID).getLastName());
    }
    
    @Test
    void testUpdatePhone() {
        Contact contact = createValidContact();
        service.addContact(contact);
        
        service.updatePhone(VALID_ID, "9876543210");
        assertEquals("9876543210", service.getContact(VALID_ID).getPhone());
    }
    
    @Test
    void testUpdateAddress() {
        Contact contact = createValidContact();
        service.addContact(contact);
        
        service.updateAddress(VALID_ID, "456 Amara Ave");
        assertEquals("456 Amara Ave", service.getContact(VALID_ID).getAddress());
    }
    
    @Test
    void testUpdateNonExistentContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateFirstName("NONEXISTENT", "Jane");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateLastName("NONEXISTENT", "Smith");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            service.updatePhone("NONEXISTENT", "9876543210");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateAddress("NONEXISTENT", "456 Amara Ave");
        });
    }
    
    @Test
    void testUpdateWithInvalidData() {
        Contact contact = createValidContact();
        service.addContact(contact);
        
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateFirstName(VALID_ID, null);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateFirstName(VALID_ID, "NoWayThisNameIsWayTooLong");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            service.updatePhone(VALID_ID, "123");
        });
        
        assertEquals(VALID_FIRST_NAME, service.getContact(VALID_ID).getFirstName());
        assertEquals(VALID_PHONE, service.getContact(VALID_ID).getPhone());
    }
    
    // Getting Contact 
    
    @Test
    void testGetContactNullId() {
        assertNull(service.getContact(null));
    }
    
    @Test
    void testGetContactNonExistent() {
        assertNull(service.getContact("NONEXISTENT"));
    }
    
    
    @Test
    void testGetAllContactIds() {
        service.addContact(createValidContact("ID1"));
        service.addContact(createValidContact("ID2"));
        
        assertEquals(2, service.getAllContactIds().size());
        assertTrue(service.getAllContactIds().contains("ID1"));
        assertTrue(service.getAllContactIds().contains("ID2"));
    }
    
    @Test
    void testContactExists() {
        assertFalse(service.contactExists(VALID_ID));
        
        service.addContact(createValidContact());
        assertTrue(service.contactExists(VALID_ID));
        assertFalse(service.contactExists(null));
    }
    
    @Test
    void testServiceStartsEmpty() {
        assertEquals(0, service.getContactCount());
        assertTrue(service.getAllContactIds().isEmpty());
    }
}