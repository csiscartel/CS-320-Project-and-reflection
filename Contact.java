package contact;

import java.util.Objects;

//Contact class 
  // Requirements: Unique ID (1 to 10 chars, not null, and immutable), First name (1 to 10 chars, and not null), Last name (1 to 10 chars, and not null), Phone (exactly 10 digits, and not null), Address (1 to 30 chars, and  not null)
 
public class Contact {
    private final String contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    

    public Contact(String contactId, String firstName, String lastName, 
                   String phone, String address) {
        
        // Validating contactId
        validateContactId(contactId);
        
        // Validating firstName
        validateFirstName(firstName);
        
        // Validating lastName
        validateLastName(lastName);
        
        // Validating phone
        validatePhone(phone);
        
        // Validating address
        validateAddress(address);
        
        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }
    
    // Validation Methods 
    
    private void validateContactId(String contactId) {
        if (contactId == null) {
            throw new IllegalArgumentException("Contact should not be null");
        }
        if (contactId.trim().isEmpty()) {
            throw new IllegalArgumentException("Contact should not be empty");
        }
        if (contactId.length() > 10) {
            throw new IllegalArgumentException("Contact ID should not be more than 10 characters");
        }
    }
    
    private void validateFirstName(String firstName) {
        if (firstName == null) {
            throw new IllegalArgumentException("First name should not be null");
        }
        if (firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name should not be empty");
        }
        if (firstName.length() > 10) {
            throw new IllegalArgumentException("First name should not exceed 10 characters");
        }
    }
    
    private void validateLastName(String lastName) {
        if (lastName == null) {
            throw new IllegalArgumentException("Last name should not be null");
        }
        if (lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name should not be empty");
        }
        if (lastName.length() > 10) {
            throw new IllegalArgumentException("Last name should not exceed 10 characters");
        }
    }
    
    private void validatePhone(String phone) {
        if (phone == null) {
            throw new IllegalArgumentException("Phone should not be null");
        }
        if (phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone should not be empty");
        }
        if (phone.length() != 10) {
            throw new IllegalArgumentException("Phone must be exactly 10 digits");
        }
        if (!phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("Phone number must be only digits 0 to 9");
        }
    }
    
    private void validateAddress(String address) {
        if (address == null) {
            throw new IllegalArgumentException("Address should not be null");
        }
        if (address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address should not be empty");
        }
        if (address.length() > 30) {
            throw new IllegalArgumentException("Address should not be more than 30 characters");
        }
    }
    
    // Getters 
    
    public String getContactId() {
        return contactId;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public String getAddress() {
        return address;
    }
    
    // Setters
    
    public void setFirstName(String firstName) {
        validateFirstName(firstName);
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        validateLastName(lastName);
        this.lastName = lastName;
    }
    
    public void setPhone(String phone) {
        validatePhone(phone);
        this.phone = phone;
    }
    
    public void setAddress(String address) {
        validateAddress(address);
        this.address = address;
    }
    
    // Object Overrides
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return contactId.equals(contact.contactId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(contactId);
    }
    
    @Override
    public String toString() {
        return "Contact{" +
                "ID='" + contactId + '\'' +
                ", name='" + firstName + " " + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}