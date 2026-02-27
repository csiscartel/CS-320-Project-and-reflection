package contact;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;


 // Unit tests for Contact class.
 // Testing all getter/setter methods validation rules 
class ContactTest {
    
    private static final String VALID_ID = "12345";
    private static final String VALID_FIRST_NAME = "Moses";
    private static final String VALID_LAST_NAME = "Kai";
    private static final String VALID_PHONE = "1234567890";
    private static final String VALID_ADDRESS = "43 abbott St";
    
    @Test
    void testValidContactCreation() {
        Contact contact = new Contact(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, 
                                     VALID_PHONE, VALID_ADDRESS);
        
        assertEquals(VALID_ID, contact.getContactId());
        assertEquals(VALID_FIRST_NAME, contact.getFirstName());
        assertEquals(VALID_LAST_NAME, contact.getLastName());
        assertEquals(VALID_PHONE, contact.getPhone());
        assertEquals(VALID_ADDRESS, contact.getAddress());
    }
    
    // Contact ID     
    @Test
    void testContactIdNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(null, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE, VALID_ADDRESS);
        });
    }
    
    @Test
    void testContactIdEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("", VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE, VALID_ADDRESS);
        });
    }
    
    @Test
    void testContactIdBlank() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("   ", VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE, VALID_ADDRESS);
        });
    }
    
    @Test
    void testContactIdTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345678901", VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE, VALID_ADDRESS);
        });
    }
    
    @Test
    void testContactIdMaxLength() {
        String maxId = "1234567890"; // 10 chars
        Contact contact = new Contact(maxId, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE, VALID_ADDRESS);
        assertEquals(maxId, contact.getContactId());
    }
    
    // Testing First Name 
    
    @Test
    void testFirstNameNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(VALID_ID, null, VALID_LAST_NAME, VALID_PHONE, VALID_ADDRESS);
        });
    }
    
    @Test
    void testFirstNameEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(VALID_ID, "", VALID_LAST_NAME, VALID_PHONE, VALID_ADDRESS);
        });
    }
    
    @Test
    void testFirstNameTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(VALID_ID, "12345678901", VALID_LAST_NAME, VALID_PHONE, VALID_ADDRESS);
        });
    }
    
    @Test
    void testFirstNameMaxLength() {
        String maxFirst = "1234567890"; // 10 chars
        Contact contact = new Contact(VALID_ID, maxFirst, VALID_LAST_NAME, VALID_PHONE, VALID_ADDRESS);
        assertEquals(maxFirst, contact.getFirstName());
    }
    
    // Testing Last Name 
    
    @Test
    void testLastNameNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(VALID_ID, VALID_FIRST_NAME, null, VALID_PHONE, VALID_ADDRESS);
        });
    }
    
    @Test
    void testLastNameEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(VALID_ID, VALID_FIRST_NAME, "", VALID_PHONE, VALID_ADDRESS);
        });
    }
    
    @Test
    void testLastNameTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(VALID_ID, VALID_FIRST_NAME, "12345678901", VALID_PHONE, VALID_ADDRESS);
        });
    }
    
    @Test
    void testLastNameMaxLength() {
        String maxLast = "1234567890"; // 10 chars
        Contact contact = new Contact(VALID_ID, VALID_FIRST_NAME, maxLast, VALID_PHONE, VALID_ADDRESS);
        assertEquals(maxLast, contact.getLastName());
    }
    
    // Testing Phone
    
    @Test
    void testPhoneNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, null, VALID_ADDRESS);
        });
    }
    
    @Test
    void testPhoneEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, "", VALID_ADDRESS);
        });
    }
    
    @Test
    void testPhoneTooShort() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, "123456789", VALID_ADDRESS);
        });
    }
    
    @Test
    void testPhoneTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, "12345678901", VALID_ADDRESS);
        });
    }
    
    @Test
    void testPhoneNonDigits() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, "123-456-7890", VALID_ADDRESS);
        });
    }
    
    @Test
    void testPhoneWithLetters() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, "12345babah", VALID_ADDRESS);
        });
    }
    
    // Testing Address     
    @Test
    void testAddressNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE, null);
        });
    }
    
    @Test
    void testAddressEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE, "");
        });
    }
    
    @Test
    void testAddressTooLong() {
        String longAddress = "This address is longer than the thirty characters in the requirement";
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE, longAddress);
        });
    }
    
    @Test
    void testAddressMaxLength() {
        String maxAddress = "123456789012345678901234567890"; // 30 chars
        Contact contact = new Contact(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE, maxAddress);
        assertEquals(maxAddress, contact.getAddress());
    }
    
    // Testing Setter 
    
    @Test
    void testSetFirstNameValid() {
        Contact contact = new Contact(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE, VALID_ADDRESS);
        contact.setFirstName("Kane");
        assertEquals("Kane", contact.getFirstName());
    }
    
    @Test
    void testSetFirstNameInvalid() {
        Contact contact = new Contact(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE, VALID_ADDRESS);
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setFirstName("NoWayThisNameIsTooLong");
        });
        assertEquals(VALID_FIRST_NAME, contact.getFirstName());
    }
    
    @Test
    void testSetLastNameValid() {
        Contact contact = new Contact(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE, VALID_ADDRESS);
        contact.setLastName("Swary");
        assertEquals("Swary", contact.getLastName());
    }
    
    @Test
    void testSetPhoneValid() {
        Contact contact = new Contact(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE, VALID_ADDRESS);
        contact.setPhone("9876543210");
        assertEquals("9876543210", contact.getPhone());
    }
    
    @Test
    void testSetAddressValid() {
        Contact contact = new Contact(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE, VALID_ADDRESS);
        contact.setAddress("456 Amara Ave");
        assertEquals("456 Amara Ave", contact.getAddress());
    }
    
    // Testing Object Method     
    @Test
    void testEqualsAndHashCode() {
        Contact contact1 = new Contact("ID1", "Moses", "Kai", "1234567890", "Addr1");
        Contact contact2 = new Contact("ID1", "Kane", "Swary", "9876543210", "Addr2");
        Contact contact3 = new Contact("ID2", "Moses", "Kai", "1234567890", "Addr1");
        
        assertEquals(contact1, contact2);
        assertNotEquals(contact1, contact3);
        assertEquals(contact1.hashCode(), contact2.hashCode());
        assertNotEquals(contact1.hashCode(), contact3.hashCode());
    }
    
    @Test
    void testToString() {
        Contact contact = new Contact(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_PHONE, VALID_ADDRESS);
        assertNotNull(contact.toString());
        assertTrue(contact.toString().contains(VALID_ID));
    }
}