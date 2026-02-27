package appointment;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

/**
 * Managing Appointment objects (service class).
 * Give in-memory storage with CRUD operations.
 * Requirements:Set appointments with unique ID, Delete appointment one ID, and delete appointments using ID, Adding appointments with unique ID, and deleting appointments by ID
 */
public class AppointmentService {
    
    private final Map<String, Appointment> appointments = new HashMap<>();
    
// Adding new appointment with unique ID.


    public void addAppointment(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment should not be null");
        }
        
        String id = appointment.getAppointmentId();
        
        if (appointments.containsKey(id)) {
            throw new IllegalArgumentException("Appointment ID already exists: " + id);
        }
        
        appointments.put(id, appointment);
    }

   // Delete ID

    public void deleteAppointment(String appointmentId) {
        if (appointmentId == null) {
            throw new IllegalArgumentException("Appointment ID should not be null");
        }
        
        if (!appointments.containsKey(appointmentId)) {
            throw new IllegalArgumentException("Not found: " + appointmentId);
        }
        
        appointments.remove(appointmentId);
    }
    
 
     // Retrieves ID

    public Appointment getAppointment(String appointmentId) {
        if (appointmentId == null) {
            return null;
        }
        return appointments.get(appointmentId);
    }
    

    public Set<String> getAllAppointmentIds() {
        return new HashSet<>(appointments.keySet());
    }
    

    public int getAppointmentCount() {
        return appointments.size();
    }
    
 // Checking if appointment exists.

    public boolean appointmentExists(String appointmentId) {
        if (appointmentId == null) {
            return false;
        }
        return appointments.containsKey(appointmentId);
    }
}
