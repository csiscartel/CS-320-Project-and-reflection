package appointment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import java.util.Calendar;

/**
 * AppointmentService class unit tests.
 * Testing all service operations along with edge cases.
 */
class AppointmentServiceTest {
    
    private AppointmentService service;
    
    private static final String VALID_ID = "APT001";
    private static final String VALID_DESCRIPTION = "Dental checkup";
    
    @BeforeEach
    void setUp() {
        service = new AppointmentService();
    }
    
    private Date createFutureDate(int daysInFuture) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, daysInFuture);
        return cal.getTime();
    }
    
    private Appointment createValidAppointment() {
        return new Appointment(VALID_ID, createFutureDate(1), VALID_DESCRIPTION);
    }
    
    private Appointment createValidAppointment(String id) {
        return new Appointment(id, createFutureDate(1), VALID_DESCRIPTION);
    }
    
    // Adding Appointment Tests 
    
    @Test
    void testAddAppointment() {
        Appointment appointment = createValidAppointment();
        service.addAppointment(appointment);
        
        assertEquals(appointment, service.getAppointment(VALID_ID));
        assertEquals(1, service.getAppointmentCount());
        assertTrue(service.appointmentExists(VALID_ID));
    }
    
    @Test
    void testAddNullAppointment() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.addAppointment(null);
        });
    }
    
    @Test
    void testAddDuplicateAppointment() {
        Appointment apt1 = createValidAppointment("ID1");
        Appointment apt2 = createValidAppointment("ID1");
        
        service.addAppointment(apt1);
        
        assertThrows(IllegalArgumentException.class, () -> {
            service.addAppointment(apt2);
        });
        
        assertEquals(apt1, service.getAppointment("ID1"));
        assertEquals(1, service.getAppointmentCount());
    }
    
    @Test
    void testAddMultipleAppointments() {
        Appointment apt1 = createValidAppointment("ID1");
        Appointment apt2 = createValidAppointment("ID2");
        Appointment apt3 = createValidAppointment("ID3");
        
        service.addAppointment(apt1);
        service.addAppointment(apt2);
        service.addAppointment(apt3);
        
        assertEquals(3, service.getAppointmentCount());
        assertEquals(apt1, service.getAppointment("ID1"));
        assertEquals(apt2, service.getAppointment("ID2"));
        assertEquals(apt3, service.getAppointment("ID3"));
    }
    
    // Deleting Appointment Tests
    
    @Test
    void testDeleteAppointment() {
        Appointment appointment = createValidAppointment();
        service.addAppointment(appointment);
        
        assertTrue(service.appointmentExists(VALID_ID));
        
        service.deleteAppointment(VALID_ID);
        
        assertNull(service.getAppointment(VALID_ID));
        assertFalse(service.appointmentExists(VALID_ID));
        assertEquals(0, service.getAppointmentCount());
    }
    
    @Test
    void testDeleteNonExistentAppointment() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteAppointment("NONEXISTENT");
        });
    }
    
    @Test
    void testDeleteAppointmentNullId() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteAppointment(null);
        });
    }
    
    // Getting Appointment Tests
    
    @Test
    void testGetAppointmentNullId() {
        assertNull(service.getAppointment(null));
    }
    
    @Test
    void testGetAppointmentNonExistent() {
        assertNull(service.getAppointment("NONEXISTENT"));
    }
    
    // Utiliting Method Tests 
    
    @Test
    void testGetAllAppointmentIds() {
        service.addAppointment(createValidAppointment("ID1"));
        service.addAppointment(createValidAppointment("ID2"));
        
        assertEquals(2, service.getAllAppointmentIds().size());
        assertTrue(service.getAllAppointmentIds().contains("ID1"));
        assertTrue(service.getAllAppointmentIds().contains("ID2"));
    }
    
    @Test
    void testAppointmentExists() {
        assertFalse(service.appointmentExists(VALID_ID));
        
        service.addAppointment(createValidAppointment());
        assertTrue(service.appointmentExists(VALID_ID));
        assertFalse(service.appointmentExists(null));
    }
    
    @Test
    void testServiceStartsEmpty() {
        assertEquals(0, service.getAppointmentCount());
        assertTrue(service.getAllAppointmentIds().isEmpty());
    }
}