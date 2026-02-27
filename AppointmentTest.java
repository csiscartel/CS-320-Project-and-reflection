package appointment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import java.util.Calendar;

/**
 * Appointment class unit tests.
 * Testing all getter/setter methods validation rules.
 */
class AppointmentTest {
    
    private static final String VALID_ID = "APT001";
    private static final String VALID_DESCRIPTION = "Dental checkup appointment";
    
    private Date createFutureDate(int daysInFuture) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, daysInFuture);
        return cal.getTime();
    }
    
    @Test
    void testValidAppointmentCreation() {
        Date futureDate = createFutureDate(1);
        Appointment appointment = new Appointment(VALID_ID, futureDate, VALID_DESCRIPTION);
        
        assertEquals(VALID_ID, appointment.getAppointmentId());
        assertEquals(futureDate, appointment.getAppointmentDate());
        assertEquals(VALID_DESCRIPTION, appointment.getDescription());
    }
    
    //Testing appointment IDs 
    
    @Test
    void testAppointmentIdNull() {
        Date futureDate = createFutureDate(1);
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(null, futureDate, VALID_DESCRIPTION);
        });
    }
    
    @Test
    void testAppointmentIdEmpty() {
        Date futureDate = createFutureDate(1);
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("", futureDate, VALID_DESCRIPTION);
        });
    }
    
    @Test
    void testAppointmentIdTooLong() {
        Date futureDate = createFutureDate(1);
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("12345678901", futureDate, VALID_DESCRIPTION);
        });
    }
    
    @Test
    void testAppointmentIdMaxLength() {
        Date futureDate = createFutureDate(1);
        String maxId = "1234567890"; // 10 chars
        Appointment appointment = new Appointment(maxId, futureDate, VALID_DESCRIPTION);
        assertEquals(maxId, appointment.getAppointmentId());
    }
    
    // testing appointment Dates  
    
    @Test
    void testAppointmentDateNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(VALID_ID, null, VALID_DESCRIPTION);
        });
    }
    
    @Test
    void testAppointmentDateInPast() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1); // Yesterday
        Date pastDate = cal.getTime();
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(VALID_ID, pastDate, VALID_DESCRIPTION);
        });
    }
    
    @Test
    void testAppointmentDateNow() {
        Date now = new Date();
        
        try {
            Appointment appointment = new Appointment(VALID_ID, now, VALID_DESCRIPTION);
            assertNotNull(appointment);
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("past"));
        }
    }
    
  // Testing Description 
    
    @Test
    void testDescriptionNull() {
        Date futureDate = createFutureDate(1);
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(VALID_ID, futureDate, null);
        });
    }
    
    @Test
    void testDescriptionEmpty() {
        Date futureDate = createFutureDate(1);
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(VALID_ID, futureDate, "");
        });
    }
    
    @Test
    void testDescriptionTooLong() {
        Date futureDate = createFutureDate(1);
        String longDesc = "The description is too long for 50 char limit";
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(VALID_ID, futureDate, longDesc);
        });
    }
    
    @Test
    void testDescriptionMaxLength() {
        Date futureDate = createFutureDate(1);
        String maxDesc = "12345678901234567890123456789012345678901234567890"; // 50 chars
        Appointment appointment = new Appointment(VALID_ID, futureDate, maxDesc);
        assertEquals(maxDesc, appointment.getDescription());
    }
    
    // Testing setters     
    @Test
    void testSetAppointmentDateValid() {
        Date futureDate1 = createFutureDate(1);
        Date futureDate2 = createFutureDate(2);
        
        Appointment appointment = new Appointment(VALID_ID, futureDate1, VALID_DESCRIPTION);
        appointment.setAppointmentDate(futureDate2);
        assertEquals(futureDate2, appointment.getAppointmentDate());
    }
    
    @Test
    void testSetAppointmentDateInvalid() {
        Date futureDate = createFutureDate(1);
        Date pastDate = createFutureDate(-1);
        
        Appointment appointment = new Appointment(VALID_ID, futureDate, VALID_DESCRIPTION);
        assertThrows(IllegalArgumentException.class, () -> {
            appointment.setAppointmentDate(pastDate);
        });
        assertEquals(futureDate, appointment.getAppointmentDate());
    }
    
    @Test
    void testSetDescriptionValid() {
        Date futureDate = createFutureDate(1);
        Appointment appointment = new Appointment(VALID_ID, futureDate, "Old description");
        appointment.setDescription("New updated description");
        assertEquals("New updated description", appointment.getDescription());
    }
    
    @Test
    void testSetDescriptionInvalid() {
        Date futureDate = createFutureDate(1);
        Appointment appointment = new Appointment(VALID_ID, futureDate, "Valid description");
        String longDesc = "This description exceeds the fifty character maximum limit specified in the requirements";
        assertThrows(IllegalArgumentException.class, () -> {
            appointment.setDescription(longDesc);
        });
        assertEquals("Valid description", appointment.getDescription());
    }
    
    
    @Test
    void testEqualsAndHashCode() {
        Date date = createFutureDate(1);
        Appointment apt1 = new Appointment("APT1", date, "Desc1");
        Appointment apt2 = new Appointment("APT1", createFutureDate(2), "Desc2");
        Appointment apt3 = new Appointment("APT2", date, "Desc1");
        
        assertEquals(apt1, apt2);
        assertNotEquals(apt1, apt3);
        assertEquals(apt1.hashCode(), apt2.hashCode());
        assertNotEquals(apt1.hashCode(), apt3.hashCode());
    }
    
    @Test
    void testToString() {
        Date futureDate = createFutureDate(1);
        Appointment appointment = new Appointment(VALID_ID, futureDate, VALID_DESCRIPTION);
        assertNotNull(appointment.toString());
        assertTrue(appointment.toString().contains(VALID_ID));
    }
}
