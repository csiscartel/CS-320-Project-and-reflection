package appointment;

import java.util.Date;
import java.util.Objects;

/**
 * Appointment class
 * Requirements:Unique appointment ID must be (1 to 10 chars, not null, and immutable)
 * Appointment date (not null, and should not be in the past)
 * Description (1 to 50 chars, and not null)
 */
public class Appointment {
    private final String appointmentId;
    private Date appointmentDate;
    private String description;
    

    public Appointment(String appointmentId, Date appointmentDate, String description) {
        validateAppointmentId(appointmentId);
        validateAppointmentDate(appointmentDate);
        validateDescription(description);
        
        this.appointmentId = appointmentId;
        this.appointmentDate = appointmentDate;
        this.description = description;
    }
    
    
    private void validateAppointmentId(String appointmentId) {
        if (appointmentId == null) {
            throw new IllegalArgumentException("Appointment ID should not be null");
        }
        if (appointmentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Appointment ID should not be empty");
        }
        if (appointmentId.length() > 10) {
            throw new IllegalArgumentException("Appointment ID should not be more than 10 characters");
        }
    }
    
    private void validateAppointmentDate(Date appointmentDate) {
        if (appointmentDate == null) {
            throw new IllegalArgumentException("Appointment date should not be null");
        }
        
        Date now = new Date();
        if (appointmentDate.before(now)) {
            throw new IllegalArgumentException("Appointment date should not be in the past");
        }
    }
    
    private void validateDescription(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description should not be null");
        }
        if (description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description should not be empty");
        }
        if (description.length() > 50) {
            throw new IllegalArgumentException("Description should not be more than 50 characters");
        }
    }
    
    //Getters 
    
    public String getAppointmentId() {
        return appointmentId;
    }
    
    public Date getAppointmentDate() {
        return appointmentDate;
    }
    
    public String getDescription() {
        return description;
    }
    
    // Setters 
   
    public void setAppointmentDate(Date appointmentDate) {
        validateAppointmentDate(appointmentDate);
        this.appointmentDate = appointmentDate;
    }
    
    public void setDescription(String description) {
        validateDescription(description);
        this.description = description;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return appointmentId.equals(that.appointmentId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(appointmentId);
    }
    
    @Override
    public String toString() {
        return "Appointment{" +
                "ID='" + appointmentId + '\'' +
                ", date=" + appointmentDate +
                ", description='" + description + '\'' +
                '}';
    }
}




